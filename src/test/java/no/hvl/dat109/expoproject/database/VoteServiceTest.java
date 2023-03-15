package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.primarykeys.VotesPK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @InjectMocks
    private VoteService service;

    @Mock
    private VoteRepo voteRepo;

    @Mock
    private EventRepo eventRepo;

    @Mock
    private VoterRepo voterRepo; // Trengs slik at vi ikke får nullpointerexception

    private Voter voter1AtExpo1, voter2AtExpo1, voter3AtExpo2;
    private Stand stand1AtExpo1, stand2AtExpo1, stand3AtExpo2;
    private Event expo1, expo2;
    private VotesPK voter1Stand1PK;

    @BeforeEach
    void setUp() {
        expo1 = new Event(1, "Expo1", LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        expo2 = new Event(2, "Expo2", LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        voter1AtExpo1 = new Voter("1", expo1);
        voter2AtExpo1 = new Voter("2", expo1);
        voter3AtExpo2 = new Voter("3", expo2);
        stand1AtExpo1 = new Stand(1, "Stand 1", "Stand 1", null, null, expo1);
        stand2AtExpo1 = new Stand(2, "Stand 2", "Stand 2", null, null, expo1);
        stand3AtExpo2 = new Stand(3, "Stand 3", "Stand 3", null, null, expo2);
        voter1Stand1PK = new VotesPK(voter1AtExpo1.getId(), stand1AtExpo1.getId());
    }

    @Test
    void getAllVotesInEvent() {
        List<Vote> votesExpo1 = List.of(
                new Vote(voter1AtExpo1, stand1AtExpo1, 5),
                new Vote(voter1AtExpo1, stand2AtExpo1, 4),
                new Vote(voter2AtExpo1, stand2AtExpo1, 4));

        List<Vote> allVotes = Stream.concat(votesExpo1.stream(), Stream.of(new Vote(voter3AtExpo2, stand3AtExpo2, 3)))
                .collect(Collectors.toList());

        when(voteRepo.findAll()).thenReturn(allVotes);

        List<Vote> votes = service.getAllVotesInEvent(expo1.getId());

        assertEquals(3, votes.size());
        assertEquals(votesExpo1, votes);

    }

    @Test
    void getVoteWhenExists() {
        Vote vote = new Vote(voter1AtExpo1, stand1AtExpo1, 5);

        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.of(vote));

        int stars = service.getVote(stand1AtExpo1.getId(), voter1AtExpo1.getId());

        assertEquals(5, stars);
    }

    @Test
    void getVoteWhenNotExists() {
        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.empty());

        int stars = service.getVote(stand1AtExpo1.getId(), voter1AtExpo1.getId());

        assertEquals(-1, stars);
    }

    @Test
    void registerVoteWhenNull() {
        assertThrows(NullPointerException.class, () -> service.registerVote(null));
    }

    @Test
    void registerLegalVote() {
        Vote vote = new Vote(voter1AtExpo1, stand1AtExpo1, 5);

        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.of(vote));

        when(voteRepo.save(vote)).thenReturn(vote);

        service.registerVote(vote);

        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.of(vote));

        int stars = service.getVote(stand1AtExpo1.getId(), voter1AtExpo1.getId());

        assertEquals(5, stars);
    }

    @Test
    void registerIllegalVote() {
        Vote vote6 = new Vote(voter1AtExpo1, stand1AtExpo1, 6);
        Vote voteNegative1 = new Vote(voter1AtExpo1, stand1AtExpo1, -1);

        assertThrows(IllegalArgumentException.class, () -> service.registerVote(vote6));
        assertThrows(IllegalArgumentException.class, () -> service.registerVote(voteNegative1));
    }

    @Test
    void generateVoteCodes() {
        when(eventRepo.getById(1)).thenReturn(expo1);
        List<String> codes = service.generateVoteCodes(3, 1);

        assertEquals(3, codes.size());
        assertEquals(3, codes.stream().distinct().count());
    }

}
