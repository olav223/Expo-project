package no.hvl.dat109.expoproject.primarykeys;

import javax.persistence.Column;
import java.io.Serializable;

public class UserEventPK implements Serializable {

    @Column(name = "user_id")
    private int user;

    @Column(name = "event_id")
    private int event;

    public UserEventPK(int user, int event) {
        this.user = user;
        this.event = event;
    }

    public UserEventPK() {

    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "UserEventPK{" +
                "user=" + user +
                ", event=" + event +
                '}';
    }
}
