package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VoteService implements IVoteService {

    private final VoteRepo voteRepo;
    private final VoterRepo voterRepo;

    private static final int CODE_LENGTH = 6;

    public VoteService(VoteRepo voteRepo, VoterRepo voterRepo) {
        this.voteRepo = voteRepo;
        this.voterRepo = voterRepo;
    }

    @Override
    public List<Vote> getAllVotesInEvent(int eventID) {
        List<Vote> allVotes = voteRepo.findAll();

        List<Vote> votesInEvent = allVotes.stream()
                .filter(vote -> vote.getStand().getEvent().getId() == eventID)
                .collect(Collectors.toList());

        return votesInEvent;
    }

    @Override
    public int getVote(Stand stand, Voter voter) {
        Vote vote = voteRepo.findByStandAndVoter(stand, voter);
        return vote == null ? -1 : vote.getStars();
    }

    @Override
    public void registerVote(Vote vote) {
        if (vote == null)
            throw new NullPointerException("Vote cannot be null");
        if (vote.getStars() < 0 || vote.getStars() > 5)
            throw new IllegalArgumentException("Vote must be between 0 and 5");

        voteRepo.save(vote);
    }

    @Override
    public List<String> generateVoteCodes(int nrOfCodes, Event event) {
        List<Voter> codes = new ArrayList<>(nrOfCodes);
        for (int i = 1; i <= nrOfCodes; i++) {
            codes.add(new Voter(StringUtils.left(UUID.randomUUID().toString(), CODE_LENGTH).toUpperCase(), event));
        }
        voterRepo.saveAll(codes);
        return codes.stream().map(Voter::getId).collect(Collectors.toList());
    }

}
