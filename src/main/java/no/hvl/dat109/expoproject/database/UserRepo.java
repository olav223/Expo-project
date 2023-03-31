package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

interface UserRepo extends JpaRepository<User, String> {

    /**
     * @param username Brukernavn til brukeren
     * @return Brukeren til brukernavnet, eller null
     */
    User findByUsername(String username);

    /**
     * @param accessLevel Tilgangsnivået til brukeren, 0 for admin, 1 for jury og 2 for utstiller
     * @return Brukeren til tilgangsnivået, eller null
     */
    User findByAccessLevel(int accessLevel);
}
