package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.*;

import java.util.List;

public interface IVoteService {

    /**
     * gets all votes registered in event with matching eventID
     *
     * @param eventID
     * @return a list of all votes in event
     */
    List<Vote> getAllVotesInEvent(int eventID);

    List<StandWithVote> getAllScoresInEvent(int eventID);

    /**
     * get the number of stars from the vote, given by given voter to given stand
     *
     * @param voterID
     * @param standID
     * @return number of stars in vote
     */
    int getVote(int standID, String voterID);

    /**
     * register vote in database
     *
     * @param vote
     */
    void registerVote(Vote vote);

    /**
     * generate a number of codes to the event with macthing eventID
     *
     * @param nrOfCodes
     * @param eventID
     */
    List<String> generateVoteCodes(int nrOfCodes, int eventID);

}
