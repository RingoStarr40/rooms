Тестовое задание - комнаты
==========================

> Небольшое REST-API приложение для организации КПП. Приложение логирует в БД все активности пользователей, в т.ч. и неудачные

Приложение сконфигурировано с помощью https://start.spring.io/ .
Тип проекта - Maven. Версия Spring - 2.2.6. Версия Java - 14. В качестве БД используется Oracle MySQL, ORM - Hibernate. REST-API сформировано с помощью Jersey.
 
Сруктура приложения
-------------------

Ниже приведено описание папок, в которых находятся классы и интерфейсы

      Entity/              Сущности БД
      Models/              Модели для формирования ответов на запросы в API
      Services/            Сервисы, в которых описана бизнес-логика приложения
      /                    Здесь лежит класс с методами API и конфигурационные классы
	  
Описание классов БД:

      ActivityEntity       Хранит в себе текущие активности пользователей - кто в какой комнате
      LogsEntity/          Логи
	  
Модели для работы с API
      
	  ResponseModel        Ответ сервера. Состоит из двух полей - код ответа и сообщение
	  
Классы из корневого каталога

      HibernateSessionFactoryUtil        Настройка HibernateSessionFactoryUtil
	  JerseyConfig                       Настрйока JerseyConfig
	  RoomResource                       Сам класс с методами API