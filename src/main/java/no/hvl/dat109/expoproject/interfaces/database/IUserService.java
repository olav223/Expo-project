package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface IUserService {

    /**
     * Adds a user to database
     *
     * @param user user to be added
     * @throws NullPointerException If user is null
     * @throws DataAccessException  If database error
     */
    void addUser(User user);

    /**
     * Removes a user with matching userID from database and returns this user
     *
     * @param userID The id of the user to be removed
     * @return The removed user, or null if not found
     */
    User removeUser(String userID);

    /**
     * Gets all registered users
     *
     * @return List of all users, or an empty list if none found
     */
    List<User> getAllUsers();

    /**
     * Gets a user with matching username
     *
     * @param username The username of the user to be found
     * @return The user, or null if not found
     */
    User getUser(String username);

    /**
     * Checks if a user with matching username exists
     *
     * @param username The username of the user to be checked
     * @return true if user with matching username exists, otherwise false
     */
    boolean userExists(String username);
}
