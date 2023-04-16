package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for å håndtere scores i databasen.
 */
@Repository
interface ScoreRepo extends JpaRepository<Score, Stand> {

    /**
     * Finds all stands from a given event, and returns a list of containing the stands and their current vote count.
     *
     * @param eventId Id of the event
     * @return A list of all stands with votes, or an empty list if no stands were found.
     */
    @Query("SELECT sv " +
            "FROM Score sv " +
            "JOIN Stand s ON sv.id = s.id " +
            "JOIN Event e ON s.eventID = e.id " +
            "WHERE e.id = ?1")
    List<Score> findAllByEventId(int eventId);

}
