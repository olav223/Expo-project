package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEventServiceTest {
    @InjectMocks
    UserEventService userEventService;

    @Mock
    UserEventRepo userEventRepo;
    User user, user2, user3;
    Event event, event2;

    @BeforeEach
    void setUp(){
        user = new User("User1", new ArrayList<>());
        user2 = new User("User2", new ArrayList<>());
        user3 = null;
        event = new Event(1);
        event2 = null;
    }

    @Test
    void addUserToEvent() {
        UserEvent userEvent = new UserEvent(user, event);
        List<UserEvent> userEvents = Arrays.asList(
                userEvent
        );

        when(userEventRepo.save(userEvent)).thenReturn(userEvent);
        userEventService.addUserToEvent(userEvent);
        when(userEventRepo.findAllByEvent(event)).thenReturn(userEvents);


        assertEquals(event.getId(), userEventRepo.findAllByEvent(event).get(0).getEvent().getId());
    }
    @Test
    void addDuplicatedUser(){
        UserEvent userEvent = new UserEvent(user, event);
        List<UserEvent> userEvents = Arrays.asList(
                userEvent
        );
        when(userEventRepo.save(userEvent)).thenReturn(userEvent);
        userEventService.addUserToEvent(userEvent);
        when(userEventRepo.findAllByEvent(userEvent.getEvent())).thenReturn(userEvents);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userEventService.addUserToEvent(userEvent));
        String expected = "Duplicated users are not allowed";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));

    }

    @Test
    void removeUserFromEvent() {
        UserEvent userEvent = new UserEvent(user, event);
        UserEvent userEvent1 = new UserEvent(user2, event);


        List<UserEvent> userEvents = Arrays.asList(
                userEvent,
                userEvent1
        );

        List<UserEvent> result = Arrays.asList(
                userEvent1
        );

        when(userEventRepo.save(userEvent)).thenReturn(userEvent);
        when(userEventRepo.save(userEvent1)).thenReturn(userEvent1);
        doNothing().when(userEventRepo).delete(userEvent);
        userEventService.addUserToEvent(userEvent);
        userEventService.addUserToEvent(userEvent1);
        when(userEventRepo.findAllByEvent(event)).thenReturn(userEvents);

        userEventService.removeUserFromEvent(userEvent);
        when(userEventRepo.findAllByEvent(event)).thenReturn(result);

        assertEquals(userEvents.get(0).getEvent().getId(), userEventRepo.findAllByEvent(event).get(0).getEvent().getId());
    }
    @Test
    void addUserWhenEventIsNull(){
        UserEvent userEvent = new UserEvent(user, event2);
        Exception exception = assertThrows(NullPointerException.class, () -> userEventService.addUserToEvent(userEvent));
        String expected = "Cannot add the user to event when the user or event is null";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }
    @Test
    void addNullUserToEvent(){
        UserEvent userEvent = new UserEvent(user3, event);
        Exception exception = assertThrows(NullPointerException.class, () -> userEventService.addUserToEvent(userEvent));
        String expected = "Cannot add the user to event when the user or event is null";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }
    @Test
    void removeUserThatDoesNotExist(){
        UserEvent userEvent = new UserEvent(user, event);
        UserEvent userEvent1 = new UserEvent(user2, event);
        List<UserEvent> userEvents = Arrays.asList(
                userEvent1
        );

        when(userEventRepo.save(userEvent1)).thenReturn(userEvent1);
        userEventService.addUserToEvent(userEvent1);
        when(userEventRepo.findAllByEvent(event)).thenReturn(userEvents);

        Exception exception = assertThrows(NullPointerException.class, () -> userEventService.removeUserFromEvent(userEvent));
        String expected = "Cannot remove a user that does not exist";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));

    }
}