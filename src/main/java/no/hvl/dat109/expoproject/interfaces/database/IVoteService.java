package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.Vote;

import java.util.List;

public interface IVoteService {

    /**
     * gets all votes registered in event with matching eventID
     * @param eventID
     * @return a list of all votes in event
     */
    List<Vote> getAllVotesInEvent(int eventID);

    /**
     * get the number of stars from the vote, given by given voter to given stand
     * @param voterID
     * @param standID
     * @return number of stars in vote
     */
    int getVote(int voterID, int standID);

    /**
     * register vote in database
     * @param vote
     */
    void registerVote(Vote vote);

    /**
     * generate a number of codes to the event with macthing eventID
     * @param nrOfCodes
     * @param eventID
     */
    void generateVoteCodes(int nrOfCodes, int eventID);

    /**
     * gets all vote codes from event with matching eventID
     * @param eventID
     * @return a list with vote codes
     */
    List<String> getAllVoteCodes(int eventID);
}
