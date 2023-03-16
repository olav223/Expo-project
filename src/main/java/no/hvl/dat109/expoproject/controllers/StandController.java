package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.entities.Stand;
import no.hvl.dat109.expoproject.interfaces.controllers.IStandController;
import no.hvl.dat109.expoproject.interfaces.database.IStandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stand")
public class StandController implements IStandController {

    private final IStandService ss;

    public StandController(IStandService ss) {
        this.ss = ss;
    }

    @Override
    @GetMapping
    public Stand getStand(int id) {
        return null;
    }

    @Override
    @PostMapping
    public void postUpdateInfo(Stand stand) {

    }
}
