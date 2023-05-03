package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.entities.*;
import no.hvl.dat109.expoproject.interfaces.controllers.IVoteController;
import no.hvl.dat109.expoproject.interfaces.database.IEventService;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import no.hvl.dat109.expoproject.utils.VoteUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vote")
public class VoteController implements IVoteController {

    private final IVoteService vs;
    private final IEventService es;
    private final IStandService ss;

    public VoteController(VoteService vs, EventService es, StandService ss) {
        this.vs = vs;
        this.es = es;
        this.ss = ss;
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
            Stand stand = ss.getStand(vote.getVotePK().getId_stand());
            if (stand == null) {
                throw new IllegalArgumentException("Stand does not exist");
            }

            if (es.isOpen(stand.getEventID())) {
                vs.registerVote(vote);
                return;
            }
        }
        catch (IllegalArgumentException | NullPointerException | PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event is not open");
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
    public List<Score> getScoresInEvent(@RequestParam int eventID) {
        if (eventID <= 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "EventID must be greater than 0");
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
    @GetMapping
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

    @Override
    @GetMapping("/newvoter")
    public String getNewVoterID(@RequestParam int eventID) {
        if (eventID <= 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "EventID must be greater than 0");
        }
        String code = VoteUtils.generateVoterID();
        try {
            Voter voter = vs.saveVoter(code, 1);
            if (voter != null) {
                return voter.getId();
            }
        }
        catch (PersistenceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return null;
    }

    /**
     * Checking if the code passed for the voter is valid
     *
     * @param voterId the 6-digit code for the voter
     * @return true if the code exists, false otherwise
     */
    @Override
    @GetMapping("/validate")
    public boolean validVoterID(@RequestParam String voterId) {
        if (voterId.equals("")) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "VoterID cannot be empty");
        }
        return vs.voterExists(voterId);
    }
}
