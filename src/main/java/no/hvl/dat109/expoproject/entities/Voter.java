package no.hvl.dat109.expoproject.entities;

import javax.persistence.*;

@Entity
@Table(schema = "expo")
public class Voter {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    public Voter(String id, Event event) {
        this.id = id;
        this.event = event;
    }

    public Voter() {
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
                ", event=" + event +
                '}';
    }
}
