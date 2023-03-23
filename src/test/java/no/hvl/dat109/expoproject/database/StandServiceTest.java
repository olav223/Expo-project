package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StandServiceTest {

    @InjectMocks
    private StandService service;

    @Mock
    private StandRepo standRepo;

    private Stand stand1, stand2;
    private Event event1;


    @BeforeEach
    void setUp() {
        event1 = new Event(1, "Event1", LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        stand1 = new Stand(1, "DataGram", "DataGram", "Image", "URL", event1.getId());
        stand2 = new Stand();
    }

    @Test
    void addStand() {
        when(standRepo.findById(1)).thenReturn(stand1);
        assertThrows(RuntimeException.class, () -> service.addStand(stand1));
    }

    @Test
    void notAddStand() {
        when(standRepo.findById(1)).thenReturn(null);
        assertDoesNotThrow(() -> service.addStand(stand1));
    }

    @Test
    void updateStand() {
        when(standRepo.findById(1)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> service.updateStand(stand1));
    }

    @Test
    void notUpdateStand() {
        when(standRepo.findById(1)).thenReturn(stand1);
        assertDoesNotThrow(() -> service.updateStand(stand1));
    }

    @Test
    void removeStand() {
        when(standRepo.deleteById(1)).thenReturn(stand1);
        Assertions.assertEquals(stand1, service.removeStand(1));
    }

    @Test
    void notRemoveStand() {
        when(standRepo.deleteById(1)).thenReturn(null);
        Assertions.assertNull(service.removeStand(1));
    }

    @Test
    void getStand() {
        when(service.getStand(1)).thenReturn(stand1);
        Assertions.assertEquals(stand1, standRepo.findById(1));
    }

    @Test
    void findAllByID() {
        List<Stand> check = List.of(stand1, stand2);
        doReturn(check).when(standRepo).findAllByEvent(event1.getId());
        Assertions.assertEquals(check, service.findAllByEvent(1));
    }

    @Test
    void nullFindAllByID() {
        doReturn(null).when(standRepo).findAllByEvent(2);
        assertNull(service.findAllByEvent(2));

    }
}