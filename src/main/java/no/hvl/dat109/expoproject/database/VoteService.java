package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import no.hvl.dat109.expoproject.primarykeys.VotePK;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VoteService implements IVoteService {

    private final VoteRepo voteRepo;
    private final VoterRepo voterRepo;
    private final EventRepo eventRepo;

    private static final int CODE_LENGTH = 6;
    private final StandRepo standRepo;

    public VoteService(VoteRepo voteRepo, VoterRepo voterRepo, EventRepo eventRepo,
                       StandRepo standRepo) {
        this.voteRepo = voteRepo;
        this.voterRepo = voterRepo;
        this.eventRepo = eventRepo;
        this.standRepo = standRepo;
    }

    @Override
    public List<Vote> getAllVotesInEvent(int eventID) {
        List<Vote> allVotes = voteRepo.findByStand_Event_Id(eventID);
        return allVotes;
    }

    @Override
    public int getVote(int standID, String voterID) {
        Optional<Vote> vote = voteRepo.findById(new VotePK(voterID, standID));
        return vote.map(Vote::getStars).orElse(-1);
    }

    @Override
    public void registerVote(Vote vote) {
        if (vote == null)
            throw new NullPointerException("Vote cannot be null");
        if (vote.getStars() < 0 || vote.getStars() > 5)
            throw new IllegalArgumentException("Vote must be between 0 and 5");

        try {
            voteRepo.save(vote);
        }
        catch (Exception e) {
            throw new RuntimeException("Could not save vote: " + e.getMessage());
        }
    }

    @Override
    public List<String> generateVoteCodes(int nrOfCodes, int eventID) {
        List<Voter> codes = new ArrayList<>(nrOfCodes);
        Event event = eventRepo.findById(eventID);

        if (event == null) {;
            return null;
        }

        for (int i = 1; i <= nrOfCodes; i++) {
            codes.add(new Voter(StringUtils.left(UUID.randomUUID().toString(), CODE_LENGTH).toUpperCase(), event));
        }
        voterRepo.saveAll(codes);
        return codes.stream().map(Voter::getId).collect(Collectors.toList());
    }

}
