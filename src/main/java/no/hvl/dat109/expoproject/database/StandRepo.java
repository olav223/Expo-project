package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Exhibitor;
import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for å håndtere stands i databasen.
 */
@Repository
interface StandRepo extends JpaRepository<Stand, Integer> {

    /**
     * Henter ut en Stand fra databasen med gitt primærnøkkel.
     *
     * @param id Id til standen
     * @return Standen til id, eller null hvis ingen ble funnet.
     */
    Stand findById(int id);

    /**
     * Henter ut en Stand fra databasen med gitt ansvarlig.
     *
     * @param exhibitor Brukernavnet til ansvarlig bruker
     * @return Standen til ansvarlig, eller null hvis ingen ble funnet.
     */
    Stand findByResponsibleID(String exhibitor);

    /**
     * Deletes the stand from the database with the given id (eventId)
     *
     * @param id Id of the stand
     * @return the deleted stand, or null if none was found
     */
    Stand deleteById(int id);

    /**
     * Finds and returns a list of all the stands on an event with the given eventID
     *
     * @param eventID The id of the event
     * @return A list af all stands from a given event, or an empty list if no stands were found.
     */
    @Query("select s from Stand s where s.eventID= ?1")
    List<Stand> findAllByEvent(Integer eventID);
}
