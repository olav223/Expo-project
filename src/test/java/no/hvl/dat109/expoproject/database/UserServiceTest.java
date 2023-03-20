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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest { // TODO se over tester som er kommentert ut, finnes de i UserEventServiceTest?
    @InjectMocks
    private UserService us;
    @Mock
    private UserRepo ur;
    @Mock
    private UserEventRepo userEventRepo;
    @Mock
    private EventRepo eventRepo;
    private List<User> users;
    private User user1, user2, user3, user4;
    private Event event, event2, event3;

    @BeforeEach
    void setup() {
        user1 = new User("user1", new ArrayList<>());
        user2 = new User("user2", new ArrayList<>());
        user3 = new User("user3", new ArrayList<>());
        users = new ArrayList<>();
        user4 = null;
        event = new Event(1);
        event2 = new Event(2);
        event3 = null;
    }

    @Test
    void addUser() {
        when(ur.save(user3)).thenReturn(user3);
        us.addUser(user3);
        when(ur.findByUsername("user3")).thenReturn(user3);
        assertEquals("user3", ur.findByUsername("user3").getUsername());
    }

    @Test
    void addNullUser() {
        Exception exception = assertThrows(NullPointerException.class, () -> us.addUser(user4));
        String expectedMessage = "The user cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void addANullUserToEvent() {
//        Exception exception = assertThrows(NullPointerException.class, () -> us.addUserToEvent(user4, event));
//        String expectedMessage = "user or event can not be null";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void addAUserToNullEvent() {
//        Exception exception = assertThrows(NullPointerException.class, () -> us.addUserToEvent(user1, event3));
//        String expectedMessage = "user or event can not be null";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }

    @Test
    void testAddandRemoveUser() {
        when(ur.save(user2)).thenReturn(user2);
        us.addUser(user2);
        when(ur.findByUsername("user2")).thenReturn(user2);

        assertEquals("user2", ur.findByUsername("user2").getUsername());
        doNothing().when(ur).delete(user2);
        us.removeUser("user2");
        verify(ur, times(1)).delete(user2);
    }

    @Test
    void deleteUserThatDoesNotExist() {
        Exception exception = assertThrows(NullPointerException.class, () -> us.removeUser("user4"));
        String expectedMessage = "The user was not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void removeUserThatDoesNotExist() {
//        List<Event> events = Arrays.asList(
//                event,
//                event2
//        );
//
//        us.addUserToEvent(user1, event);
//        us.addUserToEvent(user1, event);
//
//        Exception exception = assertThrows(NullPointerException.class, () -> us.removeUserFromEvent(user3, event));
//        String expectedMessage = "The user is not in the event";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void addUserToEvent() {
//        List<Event> events = Arrays.asList(
//                event,
//                event2
//        );
//
//        us.addUserToEvent(user1, event);
//        us.addUserToEvent(user1, event2);
//
//
//        assertEquals(events.get(0).getId(), user1.getUserEvents().get(0).getEvent().getId());
//        assertEquals(events.get(1).getId(), user1.getUserEvents().get(1).getEvent().getId());
//    }
//
//    @Test
//    void addAndRemoveUserFromEvent() {
//        // TODO Kaster en stackOverflowError | Mangler when() eller tilsvarende
//        us.addUserToEvent(user1, event);
//        us.addUserToEvent(user2, event);
//
//        us.removeUserFromEvent(user1, event); // FIXME
//
//        List<User> users = userEventRepo.findAllByEvent(event).stream()
//                .map(UserEvent::getUser)
//                .collect(Collectors.toList());
//
//        assertFalse(users.contains(user1));
//    }

    @Test
    void allUsers() {
        when(ur.save(user1)).thenReturn(user1);
        when(ur.save(user2)).thenReturn(user2);

        us.addUser(user1);
        us.addUser(user2);

        users = Arrays.asList(
                user1,
                user2
        );

        when(ur.findAll()).thenReturn(users);

        assertTrue(us.getAllUsers().contains(user1));
    }
}