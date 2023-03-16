package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import no.hvl.dat109.expoproject.primarykeys.UserEventPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EventRepo eventRepo;

    @Override
    public void addUser(User user) {
        if(user == null){
            throw new NullPointerException("The user cannot be null");
        }
        else
            userRepo.save(user);
    }

    @Override
    public User removeUser(String userID) {
        User user = userRepo.findByUsername(userID);

        if(user == null){
            throw new NullPointerException("The user was not found");
        }
        else
            userRepo.delete(user);

        return null;
    }

    @Override
    public void addUserToEvent(User user, Event event) {
        user.appendEvent(new UserEvent(user, event));
    }

    @Override
    public void removeUserFromEvent(User user, Event event) {
        user.removeEvent(new UserEvent(user, event));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
