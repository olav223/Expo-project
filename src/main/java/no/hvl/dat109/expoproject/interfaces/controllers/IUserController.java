package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.User;

import java.util.List;

public interface IUserController {

    /**
     * get user from username
     * @param username
     * @return user matching with username
     */
    User getUser(String username);

    /**
     * gets al user objects from database
     * @return a list of all users
     */
    List<User> GetAllUsers();

    /**
     * logs in user and returns the access level of user
     * @param username
     * @param password
     * @return access level
     */
    int postLogin(String username, String password);
}
