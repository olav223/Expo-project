package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.primarykeys.UserEventPK;

import java.util.List;

public interface IUserService {

    /**
     * ads user from user object to database
     * @param user
     */
    void addUser(User user);

    /**
     * removes user with matching userID from database and returns this user
     * @param userID
     * @return removed user
     */
    User removeUser(String userID);

    /**
     * adds the given user to the given event in the database
     * @param user
     * @param event
     */
    void addUserToEvent(User user, Event event);

    /**
     * removes user from the event where both event and user is matching the userEventPK
     * @param userEventPK
     */
    void removeUserFromEvent(User user, Event event);

    /**
     * gets all users registered inn database
     * @return list of all users
     */
    List<User> getAllUsers();
}
