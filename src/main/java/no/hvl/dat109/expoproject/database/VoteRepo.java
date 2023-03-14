package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.primarykeys.VotesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepo extends JpaRepository<Vote, VotesPK> {

    /**
     * @param userID  Id-en til
     * @param standID Id-en til standen som brukeren har stemt på
     * @return Antall stjerner brukeren har gitt standen, ellers -1
     */
    int findByStandAndVoter(int userID, int standID); // TODO test, usikker på om det skal være ID eller objekt

    /**
     * @param standID Id-en til standen som brukeren har stemt på
     * @return En liste med alle stemmer for en stand
     */
    List<Vote> findAllByStand(int standID); // TODO test, usikker på om det skal være ID eller objekt
}
