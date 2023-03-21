package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepo eventRepo;

    private Event event1,event2;
    private User user1,user2,user3;
    private UserEvent userEvent1,userEvent2,userEvent3;
    private List<User> users;
    @BeforeEach
    void setUp() {
        event1=new Event(1, "Event1", LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        event2=null;
        user1 = new User("user1", new ArrayList<>());
        user2 = new User("user2", new ArrayList<>());
        user3 = new User("user3", new ArrayList<>());
        users = new ArrayList<>();
        userEvent1=new UserEvent(user1,event1);
        userEvent2=new UserEvent(user2,event1);
        userEvent3=new UserEvent(user3,event1);

    }

    @Test
    void addEvent() {
        when(eventRepo.findById(1)).thenReturn(event1);
        assertThrows(RuntimeException.class, () -> eventService.addEvent(event1));
    }

    @Test
    void notAddEvent(){
        when(eventRepo.findById(1)).thenReturn(null);
        assertDoesNotThrow(()->eventService.addEvent(event1));
    }
    @Test
    void updateEvent(){
        when(eventRepo.findById(1)).thenReturn(null);
        assertThrows(RuntimeException.class, ()->eventService.UpdateEvent(event1));
    }
    @Test
    void notUpdateEvent(){
        when(eventRepo.findById(1)).thenReturn(event1);
        assertDoesNotThrow(()->eventService.UpdateEvent(event1));
    }
    @Test
    void removeEvent(){
        when(eventRepo.deleteById(1)).thenReturn(event1);
        Assertions.assertEquals(event1, eventService.removeEvent(1));
    }
    @Test
    void notRemoveEvent(){
        when(eventRepo.deleteById(1)).thenReturn(null);
        Assertions.assertNull(eventService.removeEvent(1));
    }
    @Test
    void TestIsOpen(){
        when(eventRepo.findById(1)).thenReturn(event1);
        when(eventRepo.findById(2)).thenReturn(event2);
        assertTrue(eventService.isOpen(1));
        assertFalse(eventService.isOpen(2));
    }
    @Test
    void getAllUsersInEvent(){
        when(eventRepo.findById(1)).thenReturn(event1);
        Assertions.assertEquals(users,eventService.getAllUsersInEvent(1));
    }

}
