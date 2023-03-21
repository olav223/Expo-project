package no.hvl.dat109.expoproject.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "total_votes", schema = "expo")
public class StandWithVote {

    @Id
    private int id;
    private String title;
    @Column(name = "total_stars")
    private int sumVotes;

    public StandWithVote() {
    }

    public StandWithVote(int id, String title, int sumVotes) {
        this.id = id;
        this.title = title;
        this.sumVotes = sumVotes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSumVotes() {
        return sumVotes;
    }

    @Override
    public String toString() {
        return "StandWithVote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sumVotes=" + sumVotes +
                '}';
    }
}
