package no.hvl.dat109.expoproject.entities;

import no.hvl.dat109.expoproject.primarykeys.VotePK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * En stemme som er gitt til en stand.
 */
@Entity
@Table(schema = "expo")
public class Vote {

    /**
     * Id til stemmer og stand.
     */
    @EmbeddedId
    private VotePK votePK;
    /**
     * Antall stjerner som er gitt til standen.
     * Kan være i intervallet [0,5]
     */
    private int stars;

    public Vote(VotePK votePK, int stars) {
        this.votePK = votePK;
        this.stars = stars;
    }

    public Vote() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (stars != vote.stars) return false;
        return Objects.equals(votePK, vote.votePK);
    }

    @Override
    public int hashCode() {
        int result = votePK != null ? votePK.hashCode() : 0;
        result = 31 * result + stars;
        return result;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public VotePK getVotePK() {
        return votePK;
    }

    public void setVotePK(VotePK votePK) {
        this.votePK = votePK;
    }
}
