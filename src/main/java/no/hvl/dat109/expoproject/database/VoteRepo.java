package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.primarykeys.VotePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface VoteRepo extends JpaRepository<Vote, VotePK> {

    /**
     * Henter alle stemmer for en stand
     *
     * @param standID Standen vi skal hente stemmer for
     * @return En liste med alle stemmer for en stand
     */
    @Query("select v from Vote v where v.votePK.id_stand = ?1")
    List<Vote> findAllByVotePK_idstand(Integer standID);

    /**
     * Henter alle stemmer for et event
     *
     * @param id Id til eventet vi skal hente stemmer for
     * @return En liste med alle stemmer for et event, ellers en tom liste
     */
    @Query("select v from Vote v join Event e on e.id = ?1")
    List<Vote> findByStand_Event_Id(Integer id);

}
