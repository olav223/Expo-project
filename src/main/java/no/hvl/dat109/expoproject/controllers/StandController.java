package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.controllers.IStandController;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stand")
public class StandController implements IStandController {

    private final IStandService ss;

    public StandController(StandService ss) {
        this.ss = ss;
    }

    @Override
    @GetMapping
    public Stand getStand(@RequestParam int id) {
        if (id < 1) {
            return null;
        }
        return ss.getStand(id);
    }

    @Override
    @PostMapping
    public Stand postUpdateInfo(@RequestBody Stand stand) {
        if (stand == null) {
            throw new IllegalArgumentException("Stand cannot be null");
        }

        ss.addStand(stand);

        return null; // TODO
    }

    @Override
    @DeleteMapping
    public Boolean removeStand(@RequestBody int standID) {
        if (standID < 1) {
            return false;
        }
        ss.removeStand(standID);
        return false; // TODO
    }
}
