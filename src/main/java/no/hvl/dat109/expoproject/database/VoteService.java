package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Score;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import no.hvl.dat109.expoproject.primarykeys.VotePK;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
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
    private final ScoreRepo scoreRepo;

    private static final int CODE_LENGTH = 6;

    public VoteService(VoteRepo voteRepo, VoterRepo voterRepo, EventRepo eventRepo, ScoreRepo scoreRepo) {
        this.voteRepo = voteRepo;
        this.voterRepo = voterRepo;
        this.eventRepo = eventRepo;
        this.scoreRepo = scoreRepo;
    }

    /**
     * Henter ut alle stemmer i en event
     *
     * @param eventID id til eventen
     * @return en liste med alle stemmer i eventen, ellers en tom liste hvis det ikke er noen stemmer
     */
    @Override
    public List<Vote> getAllVotesInEvent(int eventID) {
        List<Vote> allVotes = voteRepo.findByStand_Event_Id(eventID);
        return allVotes;
    }

    /**
     * Henter ut poengsummen til hver stand i en event
     *
     * @param eventID id til eventen
     * @return en liste med alle stander i eventen, og deres poengsum, ellers en tom liste hvis det er ingen stemmer
     */
    @Override
    public List<Score> getAllScoresInEvent(int eventID) {
        List<Score> allStandsWithVotes = scoreRepo.findAllByEventId(eventID);
        return allStandsWithVotes.stream()
                .sorted((a1, a2) -> a2.getSumVotes() - a1.getSumVotes())
                .collect(Collectors.toList());
    }

    /**
     * Henter en stemme basert på standID og voterID
     *
     * @param standID id til standen
     * @param voterID id til stemmegiveren
     * @return Antall stjerner stemmen har, ellers en feilmelding
     * @throws ResponseStatusException hvis stemmen ikke finnes
     */
    @Override
    public int getVote(int standID, String voterID) {
        Optional<Vote> vote = voteRepo.findById(new VotePK(voterID, standID));
        return vote.map(Vote::getStars).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vote not found"));
    }

    /**
     * Registerer en stemme i databasen, stemmen må være mellom 0 og 5.
     *
     * @param vote stemmen som skal registreres, skal inneholde en eksisterende standId og voterID.
     * @throws NullPointerException     hvis vote er null
     * @throws IllegalArgumentException hvis vote ikke er mellom 0 og 5
     * @throws PersistenceException     hvis stemmen ikke kan lagres, f.eks. hvis id-er ikke finnes
     */
    @Override
    public void registerVote(Vote vote) throws NullPointerException, IllegalArgumentException {
        if (vote == null)
            throw new NullPointerException("Vote cannot be null");
        if (vote.getStars() < 0 || vote.getStars() > 5)
            throw new IllegalArgumentException("Vote must be between 0 and 5");

        try {
            voteRepo.save(vote);
        }
        catch (Exception e) {
            throw new PersistenceException("Could not save vote: " + e.getMessage());
        }
    }

    @Override
    public boolean voterExists(String voterID) {
        return voterRepo.existsById(voterID);
    }

    @Override
    public Voter saveVoter(final String code, int eventID) {
        if (code == null) {
            throw new NullPointerException("Code cannot be null");
        }
        try {
            return voterRepo.save(new Voter(code, eventRepo.findById(eventID)));
        }
        catch (Exception e) {
            throw new PersistenceException("Could not save voter: " + e.getMessage());
        }
    }

    @Override
    @Deprecated
    public List<String> generateVoteCodes(int nrOfCodes, int eventID) {
        List<Voter> codes = new ArrayList<>(nrOfCodes);
        Event event = eventRepo.findById(eventID);

        if (event == null) {
            return null;
        }

        for (int i = 1; i <= nrOfCodes; i++) {
            codes.add(new Voter(StringUtils.left(UUID.randomUUID().toString(), CODE_LENGTH).toUpperCase(), event));
        }
        voterRepo.saveAll(codes);
        return codes.stream().map(Voter::getId).collect(Collectors.toList());
    }

}
