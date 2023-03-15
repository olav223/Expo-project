package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.primarykeys.VotesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepo extends JpaRepository<Vote, VotesPK> {

    /**
     * @param stand Standen som brukeren har stemt på
     * @param voter Brukeren som har stemt
     * @return Antall stjerner brukeren har gitt standen, ellers -1
     */
    int findByStandAndVoter(Stand stand, Voter voter);

    /**
     * @param stand Standen vi skal hente stemmer for
     * @return En liste med alle stemmer for en stand
     */
    List<Vote> findAllByStand(Stand stand);
}
