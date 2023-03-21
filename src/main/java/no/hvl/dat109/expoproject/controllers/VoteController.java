package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.database.VoterRepo;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.StandWithVote;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.interfaces.controllers.IVoteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController implements IVoteController {
    @Autowired
    private VoteService vs;
    @Autowired
    private EventService es;
    @Autowired
    private VoterRepo vr;

    /**
     * First site the guests land on after scanning qr code
     * @return the first site
     */
    @GetMapping
    public String getMapping(){
        //Enter one-time code site
        return "api/vote";
    }

    /**
     * Gives the view for the stand the voter wants to vote for
     * @param voterid The one time code for the voter
     * @param eventId the id for the event
     * @param standid the stand that is voted for
     * @return the voting site for the stand if the code is correct, redirects back otherwise
     */
    @GetMapping("/stand")
    public String veiwStand(@RequestParam("voterId") String voterid, @RequestParam("event") int eventId, @RequestParam("stand") int standid){
        Event event = es.findEventById(eventId);
        if(validVoterID(voterid, event))
            return "api/vote/stand/" + standid;

        return "redirect:api/vote";
    }
    /**
     * Registrerer en stemme
     *
     * @param vote Stemmen som skal gis
     * @throws ResponseStatusException hvis votePK er null, eller hvis stemmen ikke eksisterer
     */
    @Override
    @PostMapping
    public void postVote(@RequestBody final Vote vote) {
        if (vote.getVotePK() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "VotePK cannot be null");
        }
        try {
            vs.registerVote(vote);
        }
        catch (IllegalArgumentException | NullPointerException | PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Henter den totale poengsummen til hver stand i en event
     *
     * @param eventID Id til eventen
     * @return En liste med alle stander i eventen, og deres poengsum, ellers en tom liste hvis det er ingen stemmer
     * @throws ResponseStatusException hvis eventID er mindre eller lik 0
     */
    @Override
    @GetMapping("/score")
    public List<StandWithVote> getScoresInEvent(@RequestParam int eventID) {
        if (eventID <= 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "EventID cannot be 0");
        }
        return vs.getAllScoresInEvent(eventID);
    }

    /**
     * Henter en stemme fra databasen
     *
     * @param voterID Id til stemmegiveren
     * @param standID Id til standen
     * @return Stemmen, som er et tall mellom 0 og 5
     * @throws ResponseStatusException hvis voterID eller standID er tomme eller ikke eksisterende
     */
    @Override
    @GetMapping("/any")
    public int getVote(@RequestParam String voterID, @RequestParam int standID) {
        if (voterID.equals("") || standID == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "VoterID or standID cannot be empty or 0");
        }
        return vs.getVote(standID, voterID);
    }

    /**
     * Henter alle stemmer i en event
     *
     * @param eventID Id til eventen
     * @return En liste med alle stemmer i eventen, eller en tom liste hvis eventen ikke finnes
     * @throws ResponseStatusException hvis eventID er mindre eller lik 0
     */
    @Override
    @GetMapping("/all")
    public List<Vote> getVotes(@RequestParam(defaultValue = "0") int eventID) {
        if (eventID <= 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "EventID cannot be 0");
        }
        return vs.getAllVotesInEvent(eventID);
    }

    /**
     * Checking if the code passed for the voter is valid
     * @param voterId the 6-digit code for the voter
     * @param event the event the code is assigned
     * @return true if the code exists, false otherwise
     */
    @Override
    public boolean validVoterID(String voterId, Event event) {
        List<Voter> voters = vr.findAllByEvent(event);
        Voter voter = new Voter(voterId, event);

        for(Voter voter1 : voters){
            if(voter.equals(voter1)){
                return true;
            }
        }
        return false;
    }
}
