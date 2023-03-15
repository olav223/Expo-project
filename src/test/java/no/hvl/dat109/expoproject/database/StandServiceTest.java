package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StandServiceTest {

    @InjectMocks
    private StandService service;

    @Mock
    private StandRepo repo;

    private Stand stand1, stand2;

    @BeforeEach
    void setUp() {
        stand1 = new Stand();
        stand2 = new Stand();
    }

    @Test
    void addStand() {
        service.addStand(stand1);

//        Assertions.assertEquals(stand1, x);
    }

    @Test
    void updateStand() {
        service.updateStand(stand2);
    }

    @Test
    void removeStand() {
        service.removeStand(1);
    }

    @Test
    void getStand() {
        Stand y = repo.getStandById(2);
        Stand x = service.getStand(2);
        Assertions.assertEquals(y, x);
    }
}
