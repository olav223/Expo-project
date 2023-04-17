package no.hvl.dat109.expoproject.primarykeys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Primænøkkel for Vote.
 */
@Embeddable
public class VotePK implements Serializable {

    /**
     * Id til stemmer som gir stemmen
     */
    private String id_voter;
    /**
     * Id til standen som stemmen blir gitt til.
     */
    private int id_stand;

    public VotePK() {
    }

    public VotePK(String id_voter, int id_stand) {
        this.id_voter = id_voter;
        this.id_stand = id_stand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotePK votePK = (VotePK) o;

        if (id_stand != votePK.id_stand) return false;
        return Objects.equals(id_voter, votePK.id_voter);
    }

    @Override
    public int hashCode() {
        int result = id_voter != null ? id_voter.hashCode() : 0;
        result = 31 * result + id_stand;
        return result;
    }

    public String getId_voter() {
        return id_voter;
    }

    public void setId_voter(String id_voter) {
        this.id_voter = id_voter;
    }

    public int getId_stand() {
        return id_stand;
    }

    public void setId_stand(int id_stand) {
        this.id_stand = id_stand;
    }

    @Override
    public String toString() {
        return "VotesPK{" +
                "voter=" + id_voter +
                ", stand=" + id_stand +
                '}';
    }
}
