package no.hvl.dat109.expoproject.primarykeys;

import javax.persistence.Column;
import java.io.Serializable;

public class VotesPK implements Serializable {

    @Column(name = "id_voter")
    private String voter;

    @Column(name = "id_stand")
    private int stand;

    public VotesPK(String voter, int stand) {
        this.voter = voter;
        this.stand = stand;
    }

    public VotesPK() {

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
