package no.hvl.dat109.expoproject.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "expo")
public class Stand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String image;
    private String url;
    @Column(name = "event")
    private int eventID;
    @Column(name = "responsible")
    private String responsibleID;

    public Stand(int id, String title, String description, String image, String url, int eventID, String responsibleID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
        this.eventID = eventID;
        this.responsibleID = responsibleID;
    }

    public Stand(int id, String title, String description, String image, String url, int eventID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
        this.eventID = eventID;
    }

    public Stand() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getResponsibleID() {
        return responsibleID;
    }

    public void setResponsibleID(String responsibleID) {
        this.responsibleID = responsibleID;
    }

    @Override
    public String toString() {
        return "Stand{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", eventID=" + eventID +
                ", responsibleID='" + responsibleID + '\'' +
                '}';
    }
}
