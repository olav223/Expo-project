package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import no.hvl.dat109.expoproject.primarykeys.UserEventPK;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Override
    public void addUser(User user) {

    }

    @Override
    public User removeUser(int userID) {
        return null;
    }

    @Override
    public void addUserToEvent(User user, Event event) {

    }

    @Override
    public void removeUserFromEvent(UserEventPK userEventPK) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
