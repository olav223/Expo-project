package no.hvl.dat109.expoproject.interfaces.controllers;

import no.hvl.dat109.expoproject.entities.Event;

import java.util.List;

public interface IEventController {
    List<Event> getEventsByAdmin(String username);
}
