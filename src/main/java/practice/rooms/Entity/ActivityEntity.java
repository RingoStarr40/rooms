package practice.rooms.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity", schema = "rooms", catalog = "")
public class ActivityEntity {
    private int id;
    private int user;
    private int room;
    private byte inRoom;

    public ActivityEntity() {}

    public ActivityEntity(int user, int room, byte inRoom) {
        this.user = user;
        this.room = room;
        this.inRoom = inRoom;
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
    @Column(name = "User")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Basic
    @Column(name = "Room")
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Basic
    @Column(name = "InRoom")
    public byte getInRoom() {
        return inRoom;
    }

    public void setInRoom(byte inRoom) {
        this.inRoom = inRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return id == that.id &&
                user == that.user &&
                room == that.room &&
                inRoom == that.inRoom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, room, inRoom);
    }
}
