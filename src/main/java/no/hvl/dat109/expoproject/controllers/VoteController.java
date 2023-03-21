package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.*;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.interfaces.controllers.IVoteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController implements IVoteController {
    @Autowired
    private VoteService vs;
    @Autowired
    private StandService ss;
    @Autowired
    private UserService us;
    @Autowired
    private EventService es;
    @Autowired
    private VoterRepo vr;

    @GetMapping
    public String getMapping(){
        //Enter one-time code site
        return "api/vote";
    }
    @GetMapping("api/guest/stand")
    public String veiwStand(@RequestParam("voterId") String voterid, @RequestParam("event") int eventId, @RequestParam("stand") int standid){
        Event event = es.findEventById(eventId);
        if(validVoterID(voterid, event))
            return "api/guest/stand/" + standid;

        return "redirect:api/guest";
    }
    @Override
    @PostMapping
    public void postVote(@RequestBody final Vote vote) {
        if (vote.getVotePK() == null) {
            throw new NullPointerException("VotePK cannot be null");
        }
        vs.registerVote(vote);
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
            return null;
        }
        return vs.getAllVotesInEvent(eventID);
    }

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
