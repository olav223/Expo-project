package no.hvl.dat109.expoproject.database;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.primarykeys.VotesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepo extends JpaRepository<Vote, VotesPK>{

    /**
    @return a list with all the votes
     */
    List<Vote> getAllVotes();

    /**
    @param userID of the user
    @param standID of the stand
    @return number of stars if a vote contains userID and StandID, else
    @return null
     */
    int getVote(int userID, int standID);

    /**
    @param id of the event
    @return a list of votes from the event with the id
     */
    List<Vote>getAllVotesByEventID(int id);

    /**
    @param EventID of the event
    @return a list of Vote Codes from the event with the id
     */
    List<String>getAllVoteCodes(int EventID);
}
