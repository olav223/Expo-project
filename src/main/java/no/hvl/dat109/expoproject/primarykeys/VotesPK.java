package no.hvl.dat109.expoproject.primarykeys;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class VotesPK implements Serializable {

    @Column(name = "id_voter")
    private String voter;

    @Column(name = "id_stand")
    private int stand;

    public VotesPK(String voter, int stand) {
        this.voter = voter;
        this.stand = stand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotesPK votesPK = (VotesPK) o;

        if (stand != votesPK.stand) return false;
        return Objects.equals(voter, votesPK.voter);
    }

    @Override
    public int hashCode() {
        int result = voter != null ? voter.hashCode() : 0;
        result = 31 * result + stand;
        return result;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public int getStand() {
        return stand;
    }

    public void setStand(int stand) {
        this.stand = stand;
    }

    @Override
    public String toString() {
        return "VotesPK{" +
                "voter=" + voter +
                ", stand=" + stand +
                '}';
    }
}
