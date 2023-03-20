package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandService implements IStandService {
    private final StandRepo standRepo;
    private final EventRepo eventRepo;

    public StandService(StandRepo standRepo, EventRepo eventRepo) {
        this.standRepo = standRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public void addStand(Stand stand) {
        if (standRepo.findById(stand.getId()) != null) {
            throw new RuntimeException("Stand exist already");
        } else {
            standRepo.save(stand);
        }
    }

    @Override
    public void updateStand(Stand stand) {
        if (standRepo.findById(stand.getId()) == null) {
            throw new RuntimeException("Stand does not exist already");
        } else {
            standRepo.save(stand);
        }
    }

    @Override
    public Stand removeStand(int standID) {
        return standRepo.deleteById(standID);
    }

    @Override
    public Stand getStand(int id) {
        return standRepo.findById(id);
    }

    @Override
    public List<Stand> findAllByEvent(int id) {
        Event event = eventRepo.getById(id);
        if (event == null) {
            return null;
        } else {
            List<Stand> CompleteList = standRepo.findAllByEvent(event);
            return CompleteList;
        }
    }
}
