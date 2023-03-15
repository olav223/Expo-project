package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.stereotype.Service;

@Service
public class StandService implements IStandService {
    @Override
    public void addStand(Stand stand) {

    }

    @Override
    public void updateStand(Stand stand) {

    }

    @Override
    public Stand removeStand(int standID) {
        return null;
    }
}
