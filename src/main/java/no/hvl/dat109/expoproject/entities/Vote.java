package no.hvl.dat109.expoproject.entities;

import no.hvl.dat109.expoproject.primarykeys.VotePK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(schema = "expo")
//@IdClass(VotePK.class)
public class Vote {

    @EmbeddedId
    private VotePK votePK;

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
