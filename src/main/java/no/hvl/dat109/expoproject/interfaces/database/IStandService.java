package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface IStandService {

    /**
     * Find and return stand with matching exhibitor
     *
     * @param exhibitor The username of the exhibitor
     * @return The stand with matching exhibitor, or null if not found
     */
    Stand findStandByExhibitor(String exhibitor);

    /**
     * Adds stand from stand object to database
     *
     * @param stand The stand to be added to the database
     * @throws RuntimeException    If the standID already exists
     * @throws DataAccessException if database error
     */
    void addStand(Stand stand) throws DataAccessException;

    /**
     * Update stand in database from updated stand object
     *
     * @param stand The updated stand
     * @throws RuntimeException    If the standID does not exist
     * @throws DataAccessException if database error
     */
    void updateStand(Stand stand);

    /**
     * Removes stand with matching standID from database
     *
     * @param standID The id of the stand to be removed
     * @return The removed stand, or null if not found
     */
    Stand removeStand(int standID);

    /**
     * Finds and returns stand with matching id
     *
     * @param id The id of the stand to be found
     * @return Stand with matching id, or null if not found
     */
    Stand getStand(int id);

    /**
     * Finds and returns all stands in an event
     *
     * @param id The id of the event
     * @return A list of all stands in the event, or an empty list if none found
     */
    List<Stand> findAllByEvent(int id);
}
