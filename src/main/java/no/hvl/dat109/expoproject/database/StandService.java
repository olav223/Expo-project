package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    public Stand findStandByExhibitor(String exhibitor) {
        return standRepo.findByResponsibleID(exhibitor);
    }

    @Override
    public void addStand(Stand stand) throws DataAccessException {
        if (standRepo.findById(stand.getId()) != null) {
            throw new RuntimeException("Stand already exists");
        }
        standRepo.save(stand);
    }

    @Override
    public void updateStand(Stand stand) {
        if (standRepo.findById(stand.getId()) == null) {
            throw new RuntimeException("Stand does not exist already");
        }
        else {
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
        List<Stand> CompleteList = standRepo.findAllByEvent(id);
        return CompleteList;
    }

}
