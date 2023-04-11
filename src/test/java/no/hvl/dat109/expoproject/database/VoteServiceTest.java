package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.*;
import no.hvl.dat109.expoproject.primarykeys.VotePK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @InjectMocks
    private VoteService service;

    @Mock
    private VoteRepo voteRepo;
    @Mock
    private EventRepo eventRepo;
    @Mock
    private ScoreRepo scoreRepo;
    @Mock
    private VoterRepo voterRepo; // Trengs slik at vi ikke får nullpointerexception

    private Voter voter1AtExpo1, voter2AtExpo1, voter3AtExpo2;
    private Stand stand1AtExpo1, stand2AtExpo1, stand3AtExpo2;
    private Event expo1, expo2;
    private VotePK voter1Stand1PK, voter1Stand2PK, voter2Stand2PK, voter3Stand3PK;

    @BeforeEach
    void setUp() {
        expo1 = new Event(1, "Expo1", LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null);
        expo2 = new Event(2, "Expo2", LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null);
        voter1AtExpo1 = new Voter("1", expo1);
        voter2AtExpo1 = new Voter("2", expo1);
        voter3AtExpo2 = new Voter("3", expo2);
        stand1AtExpo1 = new Stand(1, "Stand 1", "Stand 1", null, null, expo1.getId());
        stand2AtExpo1 = new Stand(2, "Stand 2", "Stand 2", null, null, expo1.getId());
        stand3AtExpo2 = new Stand(3, "Stand 3", "Stand 3", null, null, expo2.getId());
        voter1Stand1PK = new VotePK(voter1AtExpo1.getId(), stand1AtExpo1.getId());
        voter1Stand2PK = new VotePK(voter1AtExpo1.getId(), stand2AtExpo1.getId());
        voter2Stand2PK = new VotePK(voter2AtExpo1.getId(), stand2AtExpo1.getId());
        voter3Stand3PK = new VotePK(voter3AtExpo2.getId(), stand3AtExpo2.getId());
    }

    @Test
    void getAllVotesInEvent() {
        List<Vote> votesExpo1 = List.of(
                new Vote(voter1Stand1PK, 5),
                new Vote(voter1Stand2PK, 4),
                new Vote(voter2Stand2PK, 4));

        when(voteRepo.findByStand_Event_Id(expo1.getId())).thenReturn(votesExpo1);

        List<Vote> votes = service.getAllVotesInEvent(expo1.getId());

        assertEquals(3, votes.size());
        assertEquals(votesExpo1, votes);
    }

    @Test
    void getAllVotesInEventWhenNoVotes() {
        when(voteRepo.findByStand_Event_Id(expo1.getId())).thenReturn(List.of());

        List<Vote> votes = service.getAllVotesInEvent(expo1.getId());

        assertEquals(0, votes.size());
    }

    @Test
    void getVoteWhenExists() {
        Vote vote = new Vote(voter1Stand1PK, 5);

        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.of(vote));

        int stars = service.getVote(stand1AtExpo1.getId(), voter1AtExpo1.getId());

        assertEquals(5, stars);
    }

    @Test
    void getVoteWhenNotExists() {
        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.getVote(stand1AtExpo1.getId(), voter1AtExpo1.getId()));
    }

    @Test
    void registerVoteWhenNull() {
        assertThrows(NullPointerException.class, () -> service.registerVote(null));
    }

    @Test
    void registerLegalVote() {
        Vote vote = new Vote(voter1Stand1PK, 5);

        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.of(vote));

        when(voteRepo.save(vote)).thenReturn(vote);

        service.registerVote(vote);

        when(voteRepo.findById(voter1Stand1PK)).thenReturn(Optional.of(vote));

        int stars = service.getVote(stand1AtExpo1.getId(), voter1AtExpo1.getId());

        assertEquals(5, stars);
    }

    @Test
    void registerIllegalVote() {
        Vote vote6 = new Vote(voter1Stand1PK, 6);
        Vote voteNegative1 = new Vote(voter1Stand1PK, -1);

        assertThrows(IllegalArgumentException.class, () -> service.registerVote(vote6));
        assertThrows(IllegalArgumentException.class, () -> service.registerVote(voteNegative1));
    }

    @Test
    void generateVoteCodes() {
        when(eventRepo.findById(1)).thenReturn(expo1);
        List<String> codes = service.generateVoteCodes(3, 1);

        assertEquals(3, codes.size());
        assertEquals(3, codes.stream().distinct().count());
    }

    @Test
    void getScoreByExistingEventID() {
        List<Score> allStandsWithVotes = List.of(
                new Score(stand1AtExpo1.getId(), stand1AtExpo1.getTitle(), 5),
                new Score(stand2AtExpo1.getId(), stand2AtExpo1.getTitle(), 4));

        when(scoreRepo.findAllByEventId(expo1.getId())).thenReturn(allStandsWithVotes);

        List<Score> standsWithVotes = service.getAllScoresInEvent(expo1.getId());
        assertEquals(2, standsWithVotes.size());
        assertEquals(allStandsWithVotes, standsWithVotes);
    }

    @Test
    void getScoreByNonExistingEventID() {
        when(scoreRepo.findAllByEventId(expo1.getId())).thenReturn(List.of());

        List<Score> standsWithVotes = service.getAllScoresInEvent(expo1.getId());
        assertEquals(0, standsWithVotes.size());
        assertEquals(List.of(), standsWithVotes);
    }

    @Test
    void saveVoterNullInput() {
        assertThrows(NullPointerException.class, () -> service.saveVoter(null, 1));
    }

    @Test
    void saveVoterExists() {
        doThrow(PersistenceException.class).when(voterRepo).save(any());
        assertThrows(PersistenceException.class, () -> service.saveVoter(voter1AtExpo1.getId(), voter1AtExpo1.getEvent().getId()));
    }

    @Test
    void saveLegalVoter() {
        doReturn(voter1AtExpo1).when(voterRepo).save(any());
        assertEquals(voter1AtExpo1, service.saveVoter(voter1AtExpo1.getId(), voter1AtExpo1.getEvent().getId()));
    }

}
