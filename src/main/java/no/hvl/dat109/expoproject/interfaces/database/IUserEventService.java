package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.UserEvent;
import org.springframework.dao.DataAccessException;

public interface IUserEventService {

    /**
     * Add an existing user to an existing event
     *
     * @param userEvent The map between the user and the event that is added to the database
     * @throws DataAccessException If database error
     */
    void addUserToEvent(UserEvent userEvent);


    /**
     * Remove an existing user from an existing event
     *
     * @param userEvent The map we are deleting
     * @throws DataAccessException If database error
     */
    void removeUserFromEvent(UserEvent userEvent);
}
