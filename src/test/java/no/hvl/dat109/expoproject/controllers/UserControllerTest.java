package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.entities.UserEvent;
import no.hvl.dat109.expoproject.utils.PasswordUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController uc;

    @Mock
    private UserService us;

    private User user1, user2;
    private List<User> users;
    private UserEvent userEvent1;
    private List<UserEvent> userEvents;

    @BeforeEach
    void setUp() {
        user1 = new User("user1", "83894032", "jfjf@email.com", PasswordUtils.hashWithSalt("Passord", "24394jsinds"), "24394jsinds", 1, userEvents);
        user2 = new User("user2", "83894012", "jjf@email.com", PasswordUtils.hashWithSalt("Passord1", "24394jinds"), "24394jinds", 2, userEvents);

        users = new ArrayList<>();
    }

    @Test
    void getUserValidUsername() {
        when(us.getUser(user1.getUsername())).thenReturn(user1);
        User returnedUser = uc.getUser(user1.getUsername());
        assertEquals(user1, returnedUser);
    }

    @Test
    void getUserInvalidUsername() {
        when(us.getUser(user1.getUsername())).thenReturn(null);
        User returnedUser = uc.getUser(user1.getUsername());
        assertNull(returnedUser);
    }

    @Test
    void getAllUsersTest() {
        users = Arrays.asList(user1, user2);
        when(us.getAllUsers()).thenReturn(users);
        assertTrue(uc.GetAllUsers().contains(user1));
        assertTrue(uc.GetAllUsers().contains(user2));
    }

    @Test
    void legalLoginTest() {
        when(us.getUser("user1")).thenReturn(user1);
        assertEquals(user1, uc.postLogin("user1", "Passord"));
    }

    @Test
    void usernameNotFoundTest() {
        when(us.getUser("user1")).thenReturn(null);
        try {
            uc.postLogin("user1", "Passord");
            fail("Should throw exception");
        }
        catch (ResponseStatusException e) {
            assertEquals(HttpStatus.FORBIDDEN, e.getStatus());
        }
    }

    @Test
    void passwordWrongTest() {
        when(us.getUser("user1")).thenReturn(user1);
        try {
            uc.postLogin("user1", "WrongPassword");
            fail("Should throw exception");
        }
        catch (ResponseStatusException e) {
            assertEquals(HttpStatus.FORBIDDEN, e.getStatus());
        }
    }

}
