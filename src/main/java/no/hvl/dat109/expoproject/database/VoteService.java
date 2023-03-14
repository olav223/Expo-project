package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService implements IVoteService {

    private final VoteRepo voteRepo;
    private final VoterRepo voterRepo;

    public VoteService(VoteRepo voteRepo, VoterRepo voterRepo) {
        this.voteRepo = voteRepo;
        this.voterRepo = voterRepo;
    }

    @Override
    public List<Vote> getAllVotesInEvent(int eventId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getVote(int voteId, int standId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerVote(Vote vote) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void generateVoteCodes(int nrOfCodes, int eventId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getAllVoteCodes(int eventId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
