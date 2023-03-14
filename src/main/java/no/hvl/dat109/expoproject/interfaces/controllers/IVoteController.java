package no.hvl.dat109.expoproject.interfaces.controllers;

public interface IVoteController {

    /**
     * post the given vote
     * @param userID
     * @param standID
     * @param stars
     * @return true if post of vote is OK
     */
    boolean postVote(int userID, int standID, int stars);

    /**
     * get the number of stars in the vote from given user for the given stand
     * @param userID
     * @param standID
     * @return number of stars in vote
     */
    int getVote(int userID, int standID);

}
