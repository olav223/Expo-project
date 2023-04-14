package no.hvl.dat109.expoproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * En utstiller er en person som står på en stand i en event.
 */
@Entity
@Table(schema = "expo")
public class Exhibitor {

    /**
     * Den unike id-en til en utstiller.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
     * Fornavnet til utstilleren.
     */
    @Column(name = "firstname")
    private String firstName;
    /**
     * Etternavnet til utstilleren.
     */
    @Column(name = "lastname")
    private String lastName;
    /**
     * Telefonnummeret til utstilleren.
     */
    private String phone;
    /**
     * Standen som utstilleren står på.
     */
    @ManyToOne
    @JoinColumn(name = "stand")
    @JsonIgnore
    private Stand stand;

    public Exhibitor(String id, String firstName, String lastName, String phone, Stand stand) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.stand = stand;
    }

    public Exhibitor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    @Override
    public String toString() {
        return "Exhibitor{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", stand=" + stand +
                '}';
    }
}
