package practice.rooms.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "logs", schema = "rooms", catalog = "")
public class LogsEntity {
    private int id;
    private int userId;
    private int roomId;
    private String action;
    private int code;
    private String message;
    private Timestamp date;

    public LogsEntity() {
    }

    public LogsEntity(int userId, int roomId, String action, int code, String message) {
        this.userId = userId;
        this.roomId = roomId;
        this.action = action;
        this.code = code;
        this.message = message;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "RoomId")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "Action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Basic
    @Column(name = "Code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "Date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogsEntity that = (LogsEntity) o;
        return id == that.id &&
                userId == that.userId &&
                roomId == that.roomId &&
                code == that.code &&
                Objects.equals(action, that.action) &&
                Objects.equals(message, that.message) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, roomId, action, code, message, date);
    }
}
