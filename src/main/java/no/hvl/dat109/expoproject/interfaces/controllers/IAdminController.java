package no.hvl.dat109.expoproject.interfaces.controllers;
import no.hvl.dat109.expoproject.entities.User;
public interface IAdminController {
    /**
     * adds user to event
     * @param user
     * @param eventID
     */
    void postAddUser(User user, int eventID);
}
