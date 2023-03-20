package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.database.VoterRepo;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Voter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class VoteControllerTest {
    @InjectMocks
    VoteController vc;
    @Mock
    EventService es;
    @Mock
    StandService ss;
    @Mock
    VoterRepo vr;

    String voter1, voter2, voter3, voter4;
    Event event;
    List<Voter> voterList;

    @BeforeEach
    void setUp(){
        voter1 = "123abc";
        voter2 = "321cba";
        voter3 = "213bac";
        voter4 = null;
        event = new Event(1);
        voterList = Arrays.asList(
                new Voter(voter1, event),
                new Voter(voter2, event),
                new Voter(voter3, event),
                new Voter(voter4, event)
        );
    }

    @Test
    void testPostVote() {

    }

    @Test
    void testValidVoterID() {
        when(vr.findAllByEvent(event)).thenReturn(voterList);
        assertTrue(vc.validVoterID("123abc", event));
        assertTrue(vc.validVoterID("321cba", event));
        assertFalse(vc.validVoterID("abc123", event));
    }
}