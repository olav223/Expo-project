package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Stand;

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
     * @param standID
     * @return the removed stand
     */
    Stand removeStand(int standID);

}
