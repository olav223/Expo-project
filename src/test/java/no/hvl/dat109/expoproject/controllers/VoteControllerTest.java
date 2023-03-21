package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.entities.StandWithVote;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.primarykeys.VotePK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteControllerTest {

    @InjectMocks
    private VoteController controller;
    @Mock
    private VoteService service;

    private Vote vote1_1_5stars, vote1_2_3stars, vote2_1_1star;
    private VotePK voter1Stand1, voter1Stand2, voter2Stand1;

    @BeforeEach
    void setup() {
        voter1Stand1 = new VotePK("1", 1);
        voter1Stand2 = new VotePK("1", 2);
        voter2Stand1 = new VotePK("2", 1);
        vote1_1_5stars = new Vote(voter1Stand1, 5);
        vote1_2_3stars = new Vote(voter1Stand2, 3);
        vote2_1_1star = new Vote(voter2Stand1, 1);
    }

    @Test
    void registerLegalVote() {
        doNothing().when(service).registerVote(vote1_1_5stars);
        controller.postVote(vote1_1_5stars);
    }

    @Test
    void registerVoteIllegal_notExistingVoter() {
        doThrow(ResponseStatusException.class).when(service).registerVote(vote2_1_1star);
        assertThrows(ResponseStatusException.class, () -> controller.postVote(vote2_1_1star));
    }

    @Test
    void registerVoteIllegal_notExistingStand() {
        doThrow(ResponseStatusException.class).when(service).registerVote(vote1_2_3stars);
        assertThrows(ResponseStatusException.class, () -> controller.postVote(vote1_2_3stars));
    }

    @Test
    void registerVoteWhenPKIsNull() {
        assertThrows(ResponseStatusException.class, () -> controller.postVote(new Vote(null, 0)));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 6, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void registerIllegalVote(int stars) {
        Vote illegalVote = new Vote(voter1Stand1, stars);
        doThrow(IllegalArgumentException.class).when(service).registerVote(illegalVote);
        assertThrows(ResponseStatusException.class, () -> controller.postVote(illegalVote));
    }

    @Test
    void getScoresInExistingEvent() {
        List<StandWithVote> allStandWithVotes = List.of(
                new StandWithVote(1, "Stand1", 5),
                new StandWithVote(2, "Stand2", 3));

        when(service.getAllScoresInEvent(1)).thenReturn(allStandWithVotes);
        assertEquals(allStandWithVotes, controller.getScoresInEvent(1));
    }

    @Test
    void getScoresInNonExistingEvent() {
        when(service.getAllScoresInEvent(2)).thenReturn(List.of());
        assertEquals(0, controller.getScoresInEvent(2).size());
        assertEquals(List.of(), controller.getScoresInEvent(2));
    }

    @Test
    void getVoteAtStandByVoter() {
        int stars = vote1_1_5stars.getStars();
        when(service.getVote(voter1Stand1.getId_stand(), voter1Stand1.getId_voter())).thenReturn(stars);
        assertEquals(stars, controller.getVote(voter1Stand1.getId_voter(), voter1Stand1.getId_stand()));
    }

    @Test
    void getVoteAtStandByVoter_notExistingVoter() {
        doThrow(ResponseStatusException.class).when(service).getVote(voter2Stand1.getId_stand(), voter2Stand1.getId_voter());
        assertThrows(ResponseStatusException.class, () -> controller.getVote(voter2Stand1.getId_voter(), voter2Stand1.getId_stand()));
    }

    @Test
    void getVoteAtStandByVoter_notExistingStand() {
        doThrow(ResponseStatusException.class).when(service).getVote(voter1Stand2.getId_stand(), voter1Stand2.getId_voter());
        assertThrows(ResponseStatusException.class, () -> controller.getVote(voter1Stand2.getId_voter(), voter1Stand2.getId_stand()));
    }

    @Test
    void getAllVotesInEvent() {
        List<Vote> allVotes = List.of(vote1_1_5stars, vote1_2_3stars);
        when(service.getAllVotesInEvent(1)).thenReturn(allVotes);
        List<Vote> result = controller.getVotes(1);
        assertEquals(allVotes, result);
    }

    @Test
    void getAllVotesInNonExistingEvent() {
        when(service.getAllVotesInEvent(2)).thenReturn(List.of());
        assertEquals(0, controller.getVotes(2).size());
        assertEquals(List.of(), controller.getVotes(2));
    }

}