package no.hvl.dat109.expoproject.entities;

import no.hvl.dat109.expoproject.primarykeys.UserEventPK;

import javax.persistence.*;

@Entity
@Table(schema = "expo", name = "user_event")
@IdClass(UserEventPK.class)
public class UserEvent {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_event")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private Event event;

    private int accessLevel;

    public UserEvent(User user, Event event, int accessLevel) {
        this.user = user;
        this.event = event;
        this.accessLevel = accessLevel;
    }

    public UserEvent() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "user=" + user +
                ", event=" + event +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
