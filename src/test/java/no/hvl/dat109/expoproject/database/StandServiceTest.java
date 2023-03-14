package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void addStand(Stand stand) {

        assertTrue(true);
    }

    @Test
    void updateStand(Stand stand) {
        assertTrue(true);
    }

    @Test
    Stand removeStand(int standID) {
        standID = 1;

        assertTrue(true);
        return null;

    }


}
