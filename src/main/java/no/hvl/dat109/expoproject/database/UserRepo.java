package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for å håndtere brukere i databasen.
 */
@Repository
interface UserRepo extends JpaRepository<User, String> {

    /**
     * Henter ut en bruker fra databasen etter brukernavn.
     *
     * @param username Brukernavn til brukeren
     * @return Brukeren til brukernavnet, eller null hvis ingen ble funnet.
     */
    User findByUsername(String username);

    /**
     * Henter en liste over alle brukere som har en gitt tilgangsnivå.
     *
     * @param accessLevel Tilgangsnivået til brukerne, 0 for admin, 1 for jury og 2 for utstiller
     * @return Brukerne til tilgangsnivået, eller en tom liste hvis ingen ble funnet.
     */
    List<User> findByAccessLevel(int accessLevel);

    /**
     * Checks if user with given username exists
     *
     * @param username The users username
     * @return true if user exists, otherwise false
     */
    boolean existsByUsername(String username);
}
