package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.database.StandRepo;
import no.hvl.dat109.expoproject.entities.Stand;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.List;

public interface IStandService {

    Stand findStandByExhibitor(String exhibitor);

    /**
     * adds stand from stand object to database
     * @param stand
     * @throws DataAccessException if stand already exists or database error
     */
    void addStand(Stand stand) throws DataAccessException;

    /**
     * update stand in database from updated stand object
     * @param stand
     */
    void updateStand(Stand stand);

    /**
     * removes stand with matching standID from database
     * @param standID
     * @return the removed stand
     */
    Stand removeStand(int standID);

    /**
     * @param id
     * @return stand with matching id
     */
    Stand getStand(int id);

    /**
     * @param id
     * @return a list of stands by event with matching id
     */
    List<Stand> findAllByEvent(int id);
}
