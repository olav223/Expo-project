package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.interfaces.controllers.IVoteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/guest")
public class VoteController implements IVoteController {
    @Autowired
    VoteService vs;
    @Autowired
    StandService ss;
    @Autowired
    UserService us;
    @Autowired
    EventService es;

    @GetMapping
    public String getMapping(){
        return "guest";
    }

    @PostMapping
    public void postVote(HttpServletRequest request){
        //Get the vote from frontend
        String voteId = request.getParameter("voteid");
        int standId = Integer.parseInt(request.getParameter("standId"));
        String standname = request.getParameter("standName");
        int stars = Integer.parseInt(request.getParameter("vote"));
        int eventId = Integer.parseInt(request.getParameter("event"));

        //Dette endres når servicene er implementert

        Event event = new Event(eventId);
        Voter voter = new Voter(voteId, event);
        Stand stand = new Stand(standId, standname, event);
        Vote vote = new Vote(voter, stand, stars);

        register(vote);

    }
    public void register(Vote vote){
        vs.registerVote(vote);
    }

    @Override
    public boolean postVote(String voterID, int standID, int stars) {
        return false;
    }

    @Override
    public int getVote(String voterID, int standID) {
        return vs.getVote(standID, voterID);
    }
}
