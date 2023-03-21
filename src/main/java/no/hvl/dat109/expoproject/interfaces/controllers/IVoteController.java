package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Vote;

import java.util.List;

public interface IVoteController {

    /**
     * post the given vote
     *
     * @param vote Stemmen som skal gis
     * @return true if post of vote is OK
     */
    boolean postVote(Vote vote);

    /**
     * get the number of stars in the vote from given voter for the given stand
     *
     * @param voterID
     * @param standID
     * @return number of stars in vote
     */
    int getVote(String voterID, int standID);

    /**
     * Hent alle stemmer for en event
     * @param eventID Id til eventen
     * @return En liste over alle stemmer til eventen, ellers null
     */
    List<Vote> getVotes(int eventID);

}
