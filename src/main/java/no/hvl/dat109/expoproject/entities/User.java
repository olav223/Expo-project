package no.hvl.dat109.expoproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * En bruker kan være en 'admin', 'jury' eller 'exhibitor'.
 * En bruker skal kunne logge inn, og få tilgang til det brukeren har rettigheter til.
 * accessLevel definerer hvilke rettigheter brukeren har.
 */
@Entity
@Table(schema = "expo")
public class User {

    /**
     * Brukernavnet til brukeren.
     */
    @Id
    private String username;
    /**
     * Telefonnummeret til brukeren.
     */
    private String phone;
    /**
     * Eposten til brukeren.
     */
    private String email;
    /**
     * Passordet til brukeren, hashet.
     */
    private String hash;
    /**
     * Saltet som er brukt til å hashe passordet.
     */
    private String salt;
    /**
     * Tilgangsnivået til brukeren.
     * Kan være 0: admin, 1: jury, 2: exhibitor.
     */
    private Integer accessLevel;
    /**
     * En liste over events som brukeren er knyttet til.
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserEvent> userEvents;

    public User(String username, String phone, String email, String hash, String salt, Integer accessLevel, List<UserEvent> userEvents) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.accessLevel = accessLevel;
        this.userEvents = userEvents;
    }

    public User(String username, String phone, String email, String hash, String salt, Integer accessLevel) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.accessLevel = accessLevel;
    }

    public User(String username, List<UserEvent> userEvents){
        this.username = username;
        this.userEvents = userEvents;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void appendEvent(UserEvent userEvent){
        this.userEvents.add(userEvent);
    }
    public void removeEvent(UserEvent userEvent){
        this.userEvents.remove(userEvent);
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", userEvents=" + userEvents +
                '}';
    }
}
