package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;

public interface IUserEventService {

    /**
     * @param userEvent the map between the user and the event that is added to the database
     */
    void addUserToEvent(UserEvent userEvent);


    /**
     * @param userEvent the map we are deleting
     */
    void removeUserFromEvent(UserEvent userEvent);
}
