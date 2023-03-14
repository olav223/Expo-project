package no.hvl.dat109.expoproject.entities;

import no.hvl.dat109.expoproject.primarykeys.VotesPK;

import javax.persistence.*;

@Entity
@Table(schema = "expo")
@IdClass(VotesPK.class)
public class Vote {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_stand")
    private Voter voter;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_voter")
    private Stand stand;

    private int stars;

    public Vote(Voter voter, Stand stand, int stars) {
        this.voter = voter;
        this.stand = stand;
        this.stars = stars;
    }

    public Vote() {
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
