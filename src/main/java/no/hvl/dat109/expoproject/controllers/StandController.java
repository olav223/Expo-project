package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stand")
public class StandController implements IStandService {

    private final IStandService ss;

    public StandController(StandService ss) {
        this.ss = ss;
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
}
