package no.hvl.dat109.expoproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * En event er en samling av utstillere som har en felles åpningstid.
 * Voters kan stemme på flere stands i en event, hvor det kan kåres en vinner.
 */
@Entity
@Table(schema = "expo")
public class Event {

    /**
     * Den unike id-en til en event.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Navnet på eventen.
     */
    private String name;
    /**
     * Tidspunktet eventen starter.
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime eventStart;
    /**
     * Tidspunktet eventen slutter.
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime eventEnd;
    /**
     * Bilde til eventen.
     */
    private String image;
    /**
     * En liste over alle brukere som er lagt til i denne eventen.
     */
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<UserEvent> userEvent;
    /**
     * En liste over alle stemmere som har stemt på stands i denne eventen.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Voter> voters;

    public Event(int id, String name, LocalDateTime eventStart, LocalDateTime eventEnd, String image) {
        this.id = id;
        this.name = name;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.userEvent = new ArrayList<>();
        this.voters = new ArrayList<>();
        this.image = image;
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

    public void setImage(String image) {this.image=image;}
    public String getImage() {return image;}

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
