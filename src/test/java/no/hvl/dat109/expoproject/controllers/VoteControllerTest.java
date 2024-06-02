package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.database.VoteService;
import no.hvl.dat109.expoproject.entities.*;
import no.hvl.dat109.expoproject.primarykeys.VotePK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteControllerTest {
    @InjectMocks
    private VoteController controller;
    @Mock
    private VoteService vs;
    @Mock
    private StandService ss;
    @Mock
    private EventService es;

    private String voter1, voter2, voter3, voter4;
    private Vote vote1_1_5stars, vote1_2_3stars, vote2_1_1star;
    private VotePK voter1Stand1, voter1Stand2, voter2Stand1;
    private Event event;
    private Stand stand;
    private List<Voter> voterList;

    @BeforeEach
    void setUp() {
        voter1 = "123abc";
        voter2 = "321cba";
        voter3 = "213bac";
        voter4 = null;

        voter1Stand1 = new VotePK("1", 1);
        voter1Stand2 = new VotePK("1", 2);
        voter2Stand1 = new VotePK("2", 1);
        vote1_1_5stars = new Vote(voter1Stand1, 5);
        vote1_2_3stars = new Vote(voter1Stand2, 3);
        vote2_1_1star = new Vote(voter2Stand1, 1);

        event = new Event(1);
        stand = new Stand(1, "Title", "Description", null, null, 1);

        voterList = Arrays.asList(
                new Voter(voter1, event),
                new Voter(voter2, event),
                new Voter(voter3, event),
                new Voter(voter4, event)
        );
    }

    @Test
    void testValidVoterID() {
        when(vs.voterExists(voter1)).thenReturn(true);
        assertTrue(controller.validVoterID("123abc"));
        when(vs.voterExists(voter2)).thenReturn(true);
        assertTrue(controller.validVoterID("321cba"));
    }

    @Test
    void testInvalidVoterID() {
        when(vs.voterExists(voter1)).thenReturn(false);
        assertFalse(controller.validVoterID("123abc"));
    }


    @Test
    void registerLegalVote() {
        doReturn(stand).when(ss).getStand(stand.getId());
        doReturn(true).when(es).isOpen(event.getId());
        doNothing().when(vs).registerVote(vote1_1_5stars);
        controller.postVote(vote1_1_5stars);
    }

    @Test
    void registerVoteIllegal_notExistingVoter() {
        doReturn(stand).when(ss).getStand(stand.getId());
        doReturn(true).when(es).isOpen(event.getId());
        doThrow(ResponseStatusException.class).when(vs).registerVote(vote2_1_1star);

        assertThrows(ResponseStatusException.class, () -> controller.postVote(vote2_1_1star));
    }

    @Test
    void registerVoteIllegal_notExistingStand() {
        doReturn(null).when(ss).getStand(vote1_2_3stars.getVotePK().getId_stand());
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
        doReturn(stand).when(ss).getStand(stand.getId());
        doReturn(true).when(es).isOpen(event.getId());
        doThrow(IllegalArgumentException.class).when(vs).registerVote(illegalVote);

        assertThrows(ResponseStatusException.class, () -> controller.postVote(illegalVote));
    }

    @Test
    void getScoresInExistingEvent() {
        List<Score> allScores = List.of(
                new Score(1, "Stand1", 5),
                new Score(2, "Stand2", 3));

        when(vs.getAllScoresInEvent(1)).thenReturn(allScores);
        assertEquals(allScores, controller.getScoresInEvent(1));
    }

    @Test
    void getScoresInNonExistingEvent() {
        when(vs.getAllScoresInEvent(2)).thenReturn(List.of());
        assertEquals(0, controller.getScoresInEvent(2).size());
        assertEquals(List.of(), controller.getScoresInEvent(2));
    }

    @Test
    void getVoteAtStandByVoter() {
        int stars = vote1_1_5stars.getStars();
        when(vs.getVote(voter1Stand1.getId_stand(), voter1Stand1.getId_voter())).thenReturn(stars);
        assertEquals(stars, controller.getVote(voter1Stand1.getId_voter(), voter1Stand1.getId_stand()));
    }

    @Test
    void getVoteAtStandByVoter_notExistingVoter() {
        doThrow(ResponseStatusException.class).when(vs).getVote(voter2Stand1.getId_stand(), voter2Stand1.getId_voter());
        assertThrows(ResponseStatusException.class, () -> controller.getVote(voter2Stand1.getId_voter(), voter2Stand1.getId_stand()));
    }

    @Test
    void getVoteAtStandByVoter_notExistingStand() {
        doThrow(ResponseStatusException.class).when(vs).getVote(voter1Stand2.getId_stand(), voter1Stand2.getId_voter());
        assertThrows(ResponseStatusException.class, () -> controller.getVote(voter1Stand2.getId_voter(), voter1Stand2.getId_stand()));
    }

    @Test
    void getAllVotesInEvent() {
        List<Vote> allVotes = List.of(vote1_1_5stars, vote1_2_3stars);
        when(vs.getAllVotesInEvent(1)).thenReturn(allVotes);
        List<Vote> result = controller.getVotes(1);
        assertEquals(allVotes, result);
    }

    @Test
    void getAllVotesInNonExistingEvent() {
        when(vs.getAllVotesInEvent(2)).thenReturn(List.of());
        assertEquals(0, controller.getVotes(2).size());
        assertEquals(List.of(), controller.getVotes(2));
    }

    @Test
    void generateNewVoterIdWhenIllegalInput() {
        try {
            //controller.getNewVoterID(0); TODO: Fix
            fail();
        }
        catch (ResponseStatusException e) {
            assertEquals(HttpStatus.NO_CONTENT, e.getStatus());
        }
    }

    @Test
    void generateVoterIdWhenDuplicate() {
        when(vs.saveVoter(anyString(), anyInt(), anyString(), anyString())).thenThrow(PersistenceException.class);
        try {
            //controller.getNewVoterID(1); TODO: Fix
            fail();
        }
        catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

}
