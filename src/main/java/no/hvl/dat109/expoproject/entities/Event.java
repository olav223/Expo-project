package no.hvl.dat109.expoproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(schema = "expo")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<UserEvent> userEvent;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Voter> voters;

    public Event(int id, String name, LocalDateTime eventStart, LocalDateTime eventEnd) {
        this.id = id;
        this.name = name;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.userEvent = new ArrayList<>();
        this.voters = new ArrayList<>();
    }
    public Event(int id){
        this.id = id;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalDateTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalDateTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public List<UserEvent> getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(List<UserEvent> userEvent) {
        this.userEvent = userEvent;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                '}';
    }
}
