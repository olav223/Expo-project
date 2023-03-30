package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.StandWithVote;
import no.hvl.dat109.expoproject.entities.Vote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import no.hvl.dat109.expoproject.entities.Event;

public interface IVoteController {

    /**
     * post the given vote
     *
     * @param vote Stemmen som skal gis
     * @return true if post of vote is OK
     */
    boolean postVote(Vote vote);

    @GetMapping("/score")
    List<StandWithVote> getScoresInEvent(@RequestParam int eventID);

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
    String getNewVoterID(int eventID);

    /**
     * Kontrollerer om en voterId er gyldig
     *
     * @param voterId Id til en voter
     * @return true hvis voterId er gyldig, false hvis ikke
     */
    boolean validVoterID(String voterId);
}
