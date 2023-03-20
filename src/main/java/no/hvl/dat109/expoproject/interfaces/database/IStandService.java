package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.database.StandRepo;
import no.hvl.dat109.expoproject.entities.Stand;

import java.util.List;

public interface IStandService {

    /**
     * adds stand from stand object to database
     * @param stand
     */
    void addStand(Stand stand);

    /**
     * update stand in database from updated stand object
     * @param stand
     */
    void updateStand(Stand stand);

    /**
     * removes stand with matching standID from database
     *
     * @param standID
     * @return the removed stand
     */
    Stand removeStand(int standID);

    Stand getStand(int id);

    List<Stand> findAllByEvent(int id);
}
