package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.primarykeys.UserEventPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepo extends JpaRepository<UserEvent, UserEventPK>{

    /**
    @return a list of all users in the event sorted by id
     */
    List<User> getAllUsersInEventById();
}
