package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAdminController {
    User postAddUser(User user);

    /**
     * adds user to event
     *
     * @param user
     * @param eventID
     */
    boolean postAddUser(User user, int eventID);

    /**
     * deletes user with matching username
     *
     * @param username
     * @return the deleted user
     */
    User deleteUser(String username);

    /**
     * adds new event to database??
     *
     * @param event
     */
    void postAddEvent(Event event);

    /**
     * deletes event with maching eventID
     *
     * @return deleted event
     */
    void deleteEvent(int eventID);

    /**
     * generate a number of vote codes and adds them to database
     *
     * @param nrOfCodes
     */
    void generateVoteCodes(int nrOfCodes, int eventID);
}
