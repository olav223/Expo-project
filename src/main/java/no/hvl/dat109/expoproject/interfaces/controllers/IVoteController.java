package no.hvl.dat109.expoproject.interfaces.controllers;

public interface IVoteController {

    /**
     * post the given vote
     * @param voterID
     * @param standID
     * @param stars
     * @return true if post of vote is OK
     */
    boolean postVote(String voterID, int standID, int stars);

    /**
     * get the number of stars in the vote from given voter for the given stand
     * @param voterID
     * @param standID
     * @return number of stars in vote
     */
    int getVote(String voterID, int standID);

}
