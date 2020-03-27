package practice.rooms.Services;

import practice.rooms.Entity.ActivityEntity;
import practice.rooms.Entity.LogsEntity;
import practice.rooms.HibernateSessionFactoryUtil;
import practice.rooms.Models.ResponseModel;

import static practice.rooms.HibernateSessionFactoryUtil.getSessionFactory;

public interface RoomService {

    public static ResponseModel Check(int roomId, boolean entrance, int userId) {
        //Если неправильно указан номер комнаты
        if (roomId < 1 || roomId > 5) {
            CreateLog(userId, roomId, "Вход", 500, "Неверно указана комната");
            return new ResponseModel(500, "Такой комнаты нет");
        }

        //Если неправильно указан номер пользователя
        if (userId < 1 || userId > 10000) {
            CreateLog(userId, roomId, "Вход", 500, "Неверно указан пользователь");
            return new ResponseModel(500, "Вы не являетесь пользователем нашей системы");
        }

        //Если получен запрос на вход в комнату
        if (entrance) {
            //Если пользователь уже сидит в комнате
            if (ExistActivity(roomId, userId)) {
                CreateLog(userId, roomId, "Вход", 500, "Попытка войти в комнату, где пользователь уже находится");
                return new ResponseModel(500, "Вы уже в этой комнате");
            }
            else {
                //Всё хорошо, пользователь может войти
                if (userId % roomId == 0) {
                    In(roomId, userId);
                    CreateLog(userId, roomId, "Вход", 200, "Успешно");
                    return new ResponseModel(200, "Добро пожаловать" );
                }
                //Если пользователь не имеет доступ в комнату
                else {
                    CreateLog(userId, roomId, "Вход", 403, "Запрет доступа");
                    return new ResponseModel(403, "Доступ запрещен" );
                }
            }
        }

        //Если запрос на выход из комнаты
        else {
            //Пользователь может выйти из комнаты
            if (ExistActivity(roomId, userId)) {
                Out(roomId, userId);
                CreateLog(userId, roomId, "Выход", 200, "Успешно");
                return new ResponseModel(200, "До свидания!" );
            }
            else {
                //Если предпринята попытка выйти из комнаты, в которой пользователь не находится
                CreateLog(userId, roomId, "Выход", 500, "Попытка выйти из комнаты, когда пользователь не находится в ней");
                return new ResponseModel(500, "Вы не находитесь в этой комнате" );
            }
        }
    }

    //Проверка на наличие "активной сессии" - в данной комнате находится указанный пользователь
    private static boolean ExistActivity(int roomId, int userId) {
        var session = getSessionFactory().openSession();
        var activityList = session
                .createQuery("from ActivityEntity where room = :roomId and user = :userId and inRoom = 1")
                .setParameter("roomId", roomId)
                .setParameter("userId", userId)
                .getResultList();
        return !activityList.isEmpty();
    }

    //Запись входа в комнату в БД
    private static void In(int roomId, int userId) {
        var session = getSessionFactory().openSession();
        var tr = session.beginTransaction();
        var activity = new ActivityEntity(userId, roomId, BoolToByte(true));
        session.save(activity);
        tr.commit();
        session.close();
    }

    //Запись выхода из комнаты в БД
    private static void Out(int roomId, int userId) {
        var session = getSessionFactory().openSession();
        var tr = session.beginTransaction();
        session
                .createQuery("delete ActivityEntity where room = :roomId and user = :userId ")
                .setParameter("roomId", roomId)
                .setParameter("userId", userId)
                .executeUpdate();
        tr.commit();
        session.close();
    }

    //Хак для перевода bool в tinyint
    private static byte BoolToByte(boolean pr) {
        return (byte) (pr ? 1 : 0);
    }

    //Запсиь в логи
    private static void CreateLog(int userId, int roomId, String action, int code, String message) {
        var session = getSessionFactory().openSession();
        var tr = session.beginTransaction();
        var newLog = new LogsEntity(userId, roomId, action, code, message);
        session.save(newLog);
        tr.commit();
        session.close();
    }
}
