package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StandControllerTest {

    @InjectMocks
    private StandController sc;

    @Mock
    private StandService ss;

    private Stand stand1;
    private Event event1;

    @BeforeEach
    void setUp() {
        event1 = new Event(1, "Event 1", LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        stand1 = new Stand(1, "Stand 1", "Stand 1 description", null, null, event1.getId());
    }

    @Test
    void getStandValidId() {
        when(ss.getStand(stand1.getId())).thenReturn(stand1);

        Stand returnedStand = sc.getStand(stand1.getId());
        assertEquals(stand1, returnedStand);
    }

    @Test
    void getStandInvalidId() {
        when(ss.getStand(stand1.getId())).thenReturn(null);

        Stand returnedStand = sc.getStand(stand1.getId());
        assertNull(returnedStand);
    }

    @Test
    void addStandSuccessful() {
        doNothing().when(ss).addStand(stand1);
        sc.postAddStand(stand1);
    }

    @Test
    void addStandWhenNotSuccessful() {
        doThrow(RuntimeException.class).when(ss).addStand(stand1);
        sc.postAddStand(stand1);
    }

    @Test
    void updateStandSuccessful() {
        doNothing().when(ss).updateStand(stand1);
        sc.postUpdateStand(stand1);
    }

    @Test
    void updateStandWhenNotSuccessful() {
        doThrow(RuntimeException.class).when(ss).updateStand(stand1);
        sc.postUpdateStand(stand1);
    }


    @Test
    void removeStandWithExistingID() {
        when(ss.removeStand(stand1.getId())).thenReturn(stand1);

        Boolean isRemoved = sc.removeStand(stand1.getId());
        assertTrue(isRemoved);
    }

    @Test
    void removeStandWithNonExistingID() {
        when(ss.removeStand(stand1.getId())).thenReturn(null);

        Boolean isRemoved = sc.removeStand(stand1.getId());
        assertFalse(isRemoved);
    }

}
