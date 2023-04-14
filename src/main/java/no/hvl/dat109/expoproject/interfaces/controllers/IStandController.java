package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IStandController {

    /**
     * updates stand info from updated stand object
     * @param stand
     * @return true if stand is updated
     */
    Boolean postUpdateStand(Stand stand);

    /**
     * adds new stand to standService
     * @param stand
     * @return true if stand is added
     */
    void postAddStand(Stand stand);

    /**
     * get stand with id
     * @param id
     * @return stand with matching id
     */

    Stand getStand(int id);

    /**
     * get all stands with eventID
     * @param eventID
     * @return List of stands with matching eventID
     */
    List<Stand> getAllStands(int eventID);

    /**
     * remove stand with standID
     * @param standID
     * @return true if stand is removed
     */
    Boolean removeStand(int standID);
}
