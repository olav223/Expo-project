package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import no.hvl.dat109.expoproject.primarykeys.VotesPK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.fail;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest { // FIXME

    @InjectMocks
    private VoteService service;

    @Mock
    private VoteRepo voteRepo;

    @Mock
    private VoterRepo voterRepo;

    private Voter voter1, voter2, voter3;
    private Stand stand1, stand2, stand3;
    private VotesPK voter1AndStand1PK;

    @BeforeEach
    void setUp() {
        voter1 = new Voter(1, 1);
        voter2 = new Voter(2, 2);
        voter3 = new Voter(3, 3);
        stand1 = new Stand();
        stand2 = new Stand();
        stand3 = new Stand();
        voter1AndStand1PK = new VotesPK(voter1.getId(), stand1.getId());
    }

    @Test
    void getAllVotesInEvent() {
        fail("Not yet implemented");
    }

    @Test
    void getVoteWhenExists() {
        Vote vote = new Vote(voter1AndStand1PK, 5);

        when(voteRepo.findVoteBy(voter1AndStand1PK)).thenReturn(vote);

        when(voteRepo.save(vote));

        service.registerVote(vote);

        when(voteRepo.findVoteBy(voter1AndStand1PK)).thenReturn(vote);

        int stars = service.getVote(voter1.getId(), stand1.getId());

        assertEquals(5, stars);
    }

    @Test
    void getVoteWhenNotExists() {
        when(voteRepo.findVoteBy(voter1AndStand1PK)).thenReturn(null);

        int stars = service.getVote(voter1.getId(), stand1.getId());

        assertEquals(-1, stars);
    }

    @Test
    void registerVoteWhenNull() {
        assertThrows(NullPointerException.class, () -> service.registerVote(null));
    }

    @Test
    void registerLegalVote() {
        Vote vote = new Vote(voter1, stand1, 5);

        when(voteRepo.findVoteBy(voter1AndStand1PK)).thenReturn(null);

        when(voteRepo.save(vote));

        service.registerVote(vote);

        when(voteRepo.findVoteBy(voter1AndStand1PK)).thenReturn(vote);

        int stars = service.getVote(voter1.getId(), stand1.getId());

        assertEquals(5, stars);
    }

    @Test
    void registerIllegalVote() {
        Vote vote6 = new Vote(voter1, stand1, 6);
        Vote voteNegative1 = new Vote(voter1, stand1, -1);

        assertThrows(IllegalArgumentException.class, () -> service.registerVote(vote6));
        assertThrows(IllegalArgumentException.class, () -> service.registerVote(voteNegative1));
    }

    @Test
    void generateVoteCodes() {
        List<Voter> codes = List.of(new Voter("1", null), new Voter("2", null), new Voter("3", null));

        when(voterRepo.saveAll(codes));

        service.generateVoteCodes(3, 1);

        when(voterRepo.findAll()).thenReturn(codes);

        List<String> voteCodes = service.getAllVoteCodes(1);

        assertEquals(3, voteCodes.size());
    }

    @Test
    void getAllVoteCodes() {
        fail("Not yet implemented");
    }

}
