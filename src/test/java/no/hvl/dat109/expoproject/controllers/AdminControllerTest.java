package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.EventService;
import no.hvl.dat109.expoproject.database.UserEventService;
import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import no.hvl.dat109.expoproject.utils.PasswordUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController ac;

    @Mock
    private UserService us;
    @Mock
    private EventService es;
    private UserEvent userEvent1;
    private Event event1;
    private User user1;
    private User hashedUser1;
    private String pass;
    private String hash;
    private String salt;

    @BeforeEach
    void setup() {
        salt = PasswordUtils.generateSalt();
        pass = "pass";
        user1 = new User("username", "12345678", "username@domain.com", pass, null, 0);
        event1 = new Event(1);
        userEvent1 = new UserEvent(user1, event1);
        hash = PasswordUtils.hashWithSalt(pass, salt);
        hashedUser1 = new User("username", "12345678", "username@domain.com", hash, salt, 0);

    }

    @Test
    void addUserLegal() {
        User returned = ac.postAddUser(user1);
        assertNotNull(returned);
        assertEquals(user1, returned);
    }

    @Test
    void addUserServiceError() {
        doThrow(NullPointerException.class).when(us).addUser(user1);
        try {
            ac.postAddUser(user1);
            fail();
        }
        catch (ResponseStatusException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatus());
        }
    }

    @Test
    void addExistingUser() {
        doReturn(true).when(us).userExists(hashedUser1.getUsername());
        try {
            ac.postAddUser(hashedUser1);
            fail();
        }
        catch (ResponseStatusException e) {
            assertEquals(HttpStatus.CONFLICT, e.getStatus());
        }
    }

    @Test
    void deleteUser() {
        doReturn(user1).when(us).removeUser(user1.getUsername());

        User actualUser = ac.deleteUser(user1.getUsername());

        assertEquals(user1, actualUser);
    }

    @Test
    void getEventById() {
        doReturn(event1).when(es).getEvent(event1.getId());
        Event actualEvent = ac.GetEventById(event1.getId());
        assertEquals(event1, actualEvent);
    }

    @Test
    void addEventLegal() {
        doReturn(event1).when(es).addEvent(event1);
        Event returned = ac.postAddEvent(event1);
        assertNotNull(returned);
        assertEquals(event1, returned);
    }

    @Test
    void isOpenLegalTrue() {
        doReturn(true).when(es).isOpen(event1.getId());
        boolean actual = ac.isEventOpen(event1.getId());
        assertTrue(actual);
    }

    @Test
    void isOpenLegalFalse() {
        doReturn(false).when(es).isOpen(event1.getId());
        boolean actual = ac.isEventOpen(event1.getId());
        assertFalse(actual);
    }

    @Test
    void isOpenIllegal() {
        try {
            ac.isEventOpen(-1);
            fail();
        }
        catch (ResponseStatusException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }
}
