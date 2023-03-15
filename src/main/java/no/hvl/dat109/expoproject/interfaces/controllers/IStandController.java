package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Stand;

public interface IStandController {

    /**
     * updates stand info from updated stand object
     * @param stand
     */
    Stand postUpdateInfo(Stand stand);

    /**
     * get stand with id
     * @param id
     * @return stand with matching id
     */

    Stand getStand(int id);

    Boolean removeStand(int standID);
}
