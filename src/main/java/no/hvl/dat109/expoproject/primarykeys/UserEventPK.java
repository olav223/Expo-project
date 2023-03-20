package no.hvl.dat109.expoproject.primarykeys;

import javax.persistence.Column;
import java.io.Serializable;

public class UserEventPK implements Serializable {

    @Column(name = "username")
    private String user;

    @Column(name = "id_event")
    private int event;

    public UserEventPK(String user, int event) {
        this.user = user;
        this.event = event;
    }

    public UserEventPK() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
