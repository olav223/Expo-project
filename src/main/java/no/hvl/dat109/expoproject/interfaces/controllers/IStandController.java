package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IStandController {

    /**
     * updates stand info from updated stand object
     * @param stand
     */
    Stand postUpdateStand(Stand stand);

    Stand postAddStand(Stand stand);

    /**
     * get stand with id
     * @param id
     * @return stand with matching id
     */

    Stand getStand(int id);

    List<Stand> getAllStands(int eventID);

    Boolean removeStand(int standID);
}
