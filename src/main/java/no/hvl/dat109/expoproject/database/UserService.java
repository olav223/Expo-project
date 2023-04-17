package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Metoder for å legge til, fjerne eller hente ut informasjon om Brukere fra databasen
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new NullPointerException("The user cannot be null");
        }
        else
            userRepo.save(user);

    }

    @Override
    public User removeUser(String userID) {
        User user = userRepo.findByUsername(userID);

        if (user == null) {
            //Means the user is not in the database
            throw new NullPointerException("The user was not found");
        }
        else
            userRepo.delete(user);

        return user;
    }

    public User findUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public boolean userExists(String username) {
        return userRepo.existsByUsername(username);
    }
}
