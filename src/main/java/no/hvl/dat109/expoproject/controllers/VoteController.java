package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.interfaces.controllers.IVoteController;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/vote")
public class VoteController implements IVoteController {
    private final IVoteService vs;

    public VoteController(VoteService vs) {
        this.vs = vs;
    }

    @Override
    @PostMapping
    public boolean postVote(@RequestBody final Vote vote) {
        if (vote.getVotePK() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "VotePK cannot be null");
        }
        vs.registerVote(vote);
        return true;
    }

    @Override
    @GetMapping
    public int getVote(@RequestParam String voterID, @RequestParam int standID) {
        return vs.getVote(standID, voterID);
    }

    @Override
    @GetMapping("/all")
    public List<Vote> getVotes(@RequestParam(defaultValue = "0") int eventID) {
        if (eventID == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "EventID cannot be 0");
        }
        return vs.getAllVotesInEvent(eventID);
    }
}
