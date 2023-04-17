package no.hvl.dat109.expoproject.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * En score inneholder den totale summen av stemmer en stand har fått.
 */
@Entity
@Immutable
@Table(name = "total_votes", schema = "expo")
public class Score {

    /**
     * Den unike id-en til en stand.
     */
    @Id
    private int id;
    /**
     * Navnet på standen.
     */
    private String title;
    /**
     * Den totale summen av stemmer en stand har fått.
     */
    @Column(name = "total_stars")
    private int sumVotes;

    public Score() {
    }

    public Score(int id, String title, int sumVotes) {
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
