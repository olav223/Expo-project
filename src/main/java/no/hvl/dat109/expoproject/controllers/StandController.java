package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.StandService;
import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.controllers.IStandController;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/stand")
public class StandController implements IStandController {

    private final IStandService ss;

    public StandController(StandService ss) {
        this.ss = ss;
    }

    @Override
    @GetMapping
    public Stand getStand(@RequestParam(defaultValue = "0") int id) {
        if (id < 1) {
            return null;
        }
        return ss.getStand(id);
    }

    @Override
    @GetMapping("/all")
    public List<Stand> getAllStands(@RequestParam(defaultValue = "0") int eventID) {
        if (eventID < 1) {
            return null;
        }
        return ss.findAllByEvent(eventID);
    }

    @Override
    @PostMapping("/update")
    public Boolean postUpdateStand(@RequestBody Stand stand) {
        if (stand == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Stand cannot be null");
        }

        boolean isUpdated = false;
        try {
            ss.updateStand(stand);
            isUpdated = true;
        }
        catch (RuntimeException ignored) {
        }

        return isUpdated;
    }

    @Override
    @PostMapping("/add")
    public Boolean postAddStand(@RequestBody Stand stand) {
        if (stand == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Stand cannot be null");
        }

        boolean isAdded = false;
        try {
            ss.addStand(stand);
            isAdded = true;
        }
        catch (RuntimeException ignored) {
        }

        return isAdded;
    }

    @Override
    @DeleteMapping
    public Boolean removeStand(@RequestBody int standID) {
        if (standID < 1) {
            return false;
        }
        return ss.removeStand(standID) != null;
    }
}
