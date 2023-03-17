package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import no.hvl.dat109.expoproject.primarykeys.UserEventPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private UserEventRepo userEventRepo;

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
            //Means the user is not in the database
            throw new NullPointerException("The user was not found");
        }
        else
            userRepo.delete(user);

        return user;
    }

    @Override
    public void addUserToEvent(User user, Event event) {
        if(user == null || event == null)
            throw new NullPointerException("user or event can not be null");
        else
            user.appendEvent(new UserEvent(user, event));
    }

    @Override
    public void removeUserFromEvent(User user, Event event) {
        List<UserEvent> allUsers = userEventRepo.findAllByEvent(event);

        List<User> users = allUsers.stream()
                .map(UserEvent::getUser)
                .collect(Collectors.toList());

        if(users.contains(user))
            user.removeEvent(new UserEvent(user, event));
        else
            throw new NullPointerException("The user is not in the event");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
