package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService us;
    @Mock
    private UserRepo ur;

    private User user1, user2, user3, user4;
    private Event event, event2;

    @BeforeEach
    void setup(){
        user1 = new User("user1", new ArrayList<>());
        user2 = new User("user2", new ArrayList<>());
        user3 = new User("user3", new ArrayList<>());
        user4 = null;
        event = new Event(1);
        event2 = new Event(2);
    }

    @Test
    void addUser() {
        when(ur.save(user3)).thenReturn(user3);
        us.addUser(user3);
        when(ur.findByUsername("user3")).thenReturn(user3);
        assertEquals("user3", ur.findByUsername("user3").getUsername());
    }
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
    void addUserToEvent() {
        List<Event> events = Arrays.asList(
                event,
                event2
        );

        us.addUserToEvent(user1, event);
        us.addUserToEvent(user1, event2);


        assertEquals(events.get(0).getId(), user1.getUserEvents().get(0).getEvent().getId());
        assertEquals(events.get(1).getId(), user1.getUserEvents().get(1).getEvent().getId());
    }

    @Test
    void removeUserFromEvent() {
        //Kaster en stackoverflow
        us.addUserToEvent(user2, event);
        us.addUserToEvent(user2, event2);
        us.removeUserFromEvent(user2, event);

        assertEquals(event2.getId(), user2.getUserEvents().get(0).getEvent().getId());

    }
}