package no.hvl.dat109.expoproject.interfaces.database;

import no.hvl.dat109.expoproject.entities.*;

import javax.persistence.PersistenceException;
import java.util.List;

public interface IVoteService {

    /**
     * Gets all votes registered in event with matching eventID
     *
     * @param eventID The id of event
     * @return A list of all votes in event
     */
    List<Vote> getAllVotesInEvent(int eventID);

    /**
     * Gets all scores registered in event with matching eventID
     *
     * @param eventID The id of event
     * @return A list of all scores in event
     */
    List<Score> getAllScoresInEvent(int eventID);

    /**
     * Gets the number of stars from the vote, given by given voter to given stand
     *
     * @param voterID The id of the voter
     * @param standID The id of the stand
     * @return The number of stars the voter has given to the stand (0-5)
     */
    int getVote(int standID, String voterID);

    /**
     * Register vote in database
     *
     * @param vote Stemmen som skal registreres
     * @throws NullPointerException     If vote is null
     * @throws IllegalArgumentException If vote.stars is less than 0 or greater than 5
     * @throws PersistenceException     If database error
     */
    void registerVote(Vote vote);

    /**
     * Checks if a voter with matching voterID exists
     *
     * @param voterID The id of the voter
     * @return true if voter with matching voterID exists, otherwise false
     */
    boolean voterExists(String voterID);

    Voter findByFingerprint(String fingerprint);

    /**
     * Lagrer en voterID i databasen
     *
     * @param code    Id til stemmegiveren
     * @param eventID Eventen stemmegiveren tilhører
     * @return Voter-objektet som ble lagret, ellers null
     * @throws NullPointerException Hvis code er null
     * @throws PersistenceException Hvis databasen feiler
     */
    Voter saveVoter(String code, int eventID, String ip, String fingerprint);

    /**
     * Generate a number of codes to the event with macthing eventID
     *
     * @param nrOfCodes
     * @param eventID
     */
    @Deprecated
    List<String> generateVoteCodes(int nrOfCodes, int eventID);

}
