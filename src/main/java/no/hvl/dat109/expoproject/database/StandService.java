package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.stereotype.Service;

@Service
public class StandService implements IStandService {

    private final StandRepo standRepo;

    public StandService(StandRepo standRepo) {this.standRepo = standRepo; }

    @Override
    public void addStand(Stand stand) { throw new UnsupportedOperationException("Not supported yet.");}

    @Override
    public void updateStand(Stand stand) { throw new UnsupportedOperationException("Not supported yet.");}

    @Override
    public Stand removeStand(int standID) { throw new UnsupportedOperationException("Not supported yet.");}


}
