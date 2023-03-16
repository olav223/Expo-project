package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandService implements IStandService {

    private final StandRepo standRepo;

    public StandService(StandRepo standRepo) {
        this.standRepo = standRepo;
    }

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

    @Override
    public Stand getStand(int id) {
        return null;
    }

    public List<Stand> getAllStands(int eventID) {
        return null;
    }
}
