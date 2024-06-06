package no.hvl.dat109.expoproject.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * En stemmer som kan stemme på stands.
 */
@Entity
@Table(schema = "expo")
public class Voter {

    /**
     * En unik id for stemmeren
     */
    @Id
    private String id;
    /**
     * Eventen som stemmeren hører til.
     */
    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    private String ip;
    private String fingerprint;

    public Voter() {
    }

    public Voter(String id, Event event) {
        this.id = id;
        this.event = event;
    }


    public Voter(String id, Event event, String ip, String fingerprint) {
        this.id = id;
        this.event = event;
        this.ip = ip;
        this.fingerprint = fingerprint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voter voter = (Voter) o;

        if (!Objects.equals(id, voter.id)) return false;
        return Objects.equals(event, voter.event);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "id='" + id + '\'' +
                ", eventID=" + event.getId() +
                '}';
    }
}
