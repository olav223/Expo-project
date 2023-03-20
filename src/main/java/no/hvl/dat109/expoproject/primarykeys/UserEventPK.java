package no.hvl.dat109.expoproject.primarykeys;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class UserEventPK implements Serializable {

    @Column(name = "username")
    private String user;

    @Column(name = "id_event")
    private int event;

    public UserEventPK() {

    }

    public UserEventPK(String user, int event) {
        this.user = user;
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEventPK that = (UserEventPK) o;

        if (event != that.event) return false;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + event;
        return result;
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
