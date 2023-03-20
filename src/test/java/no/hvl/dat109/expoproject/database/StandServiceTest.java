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
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@ExtendWith(MockitoExtension.class)
public class StandServiceTest {

    @InjectMocks
    private StandService service;

    @Mock
    private StandRepo repo;

    private Stand stand1, stand2;
private Event event1;


    @BeforeEach
    void setUp() {
        event1 = new Event(1, "Event1", LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        stand1 = new Stand(1, "DataGram", "DataGram", "Image", "URL", event1);
        stand2 = new Stand();
    }

    @Test
    void addStand() {
        when(repo.findById(1)).thenReturn(stand1);
        assertThrows(RuntimeException.class, () -> service.addStand(stand1) );
    }
    @Test
    void notAddStand() {
        when(repo.findById(1)).thenReturn(null);
        assertDoesNotThrow(() -> service.addStand(stand1));
    }

    @Test
    void updateStand() {
        when(repo.findById(1)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> service.updateStand(stand1) );
    }

    @Test
    void notUpdateStand() {
        when(repo.findById(1)).thenReturn(stand1);
        assertDoesNotThrow(() -> service.updateStand(stand1));
    }

    @Test
    void removeStand() {
        when(repo.deleteById(1)).thenReturn(stand1);
        Assertions.assertEquals(stand1, service.removeStand(1));
    }

    @Test
    void notRemoveStand() {
        when(repo.deleteById(1)).thenReturn(null);
        Assertions.assertNull(service.removeStand(1));
    }

    @Test
    void getStand() {
        when(service.getStand(1)).thenReturn(stand1);
        Assertions.assertEquals(stand1, repo.findById(1));
    }

    @Test
    void findAllByID() {
        List<Stand> check = List.of(stand1, stand2);
        doReturn(check).when(repo).findAllByEvent(event1);
        doReturn(check).when(repo).getById(1);
        Assertions.assertEquals(check, service.findAllByEvent(1));

    }
}