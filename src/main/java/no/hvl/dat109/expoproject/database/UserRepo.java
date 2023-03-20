package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,String>{

    /**
    @param id of the user
    @return the user with the id
     */
    User findUserById(int id);

    /**
    @return a list of all users
     */
    List<User> getAllUsers();
}
