package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandRepo extends JpaRepository<Stand, Integer> {

    /**
     * @param id of the stand
     * @return stand with the id
     */
    Stand findById(int id);

    /**
     * deletes the stand from the database with the given id (eventId)
     *
     * @param id
     * @return the deleted stand
     */
    Stand deleteById(int id);

    /**
     * finds and returns a list of all the stands on an event with the given eventID
     *
     * @param eventID
     * @return a list af all stands from a given event
     */
    @Query("select s from Stand s where s.eventID= ?1")
    List<Stand> findAllByEvent(Integer eventID);
}
