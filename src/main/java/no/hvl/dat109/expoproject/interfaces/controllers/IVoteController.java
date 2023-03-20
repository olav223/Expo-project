package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Vote;

public interface IVoteController {

    /**
     * post the given vote
     * @param voterID
     * @param standID
     * @param stars
     * @return true if post of vote is OK
     */
    void postVote(Vote vote);

    /**
     * get the number of stars in the vote from given voter for the given stand
     * @param voterID
     * @param standID
     * @return number of stars in vote
     */
    int getVote(String voterID, int standID);

}
