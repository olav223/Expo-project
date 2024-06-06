package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Score;
import no.hvl.dat109.expoproject.entities.Vote;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IVoteController {

    /**
     * post the given vote
     *
     * @param vote Stemmen som skal gis
     */
    void postVote(Vote vote);

    /**
     * Get the scores for all stands in the given event
     *
     * @param eventID Id of the event
     * @return List of all stands with their scores
     */
    List<Score> getScoresInEvent(int eventID);

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
     *
     * @param eventID Id til eventen
     * @return En liste over alle stemmer til eventen, ellers null
     */
    List<Vote> getVotes(int eventID);

    /**
     * Lager et nytt voter objekt med en unik id, basert på event.
     *
     * @param eventID Id til eventen
     * @return En unik id
     */
    String getNewVoterID(int eventID, String fingerprint, HttpServletRequest request);

    /**
     * Kontrollerer om en voterId er gyldig
     *
     * @param voterId Id til en voter
     * @return true hvis voterId er gyldig, false hvis ikke
     */
    boolean validVoterID(String voterId);
}
