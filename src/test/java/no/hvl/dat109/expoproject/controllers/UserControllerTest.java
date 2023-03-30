package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.UserRepo;
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
    @Mock
    private UserRepo ur;

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
        when(ur.findByUsername("user1")).thenReturn(user1);
        when(ur.findByUsername("user2")).thenReturn(user2);
        users=Arrays.asList(user1,user2);
        when(ur.findAll()).thenReturn(users);
        us.addUser(user1);
        us.addUser(user2);
        assertTrue(uc.GetAllUsers().contains(user1));
        assertTrue(uc.GetAllUsers().contains(user2));
    }

    @Test
    void postLoginTest() {

    }

}
