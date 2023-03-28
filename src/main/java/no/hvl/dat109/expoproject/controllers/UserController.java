package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.interfaces.controllers.IUserController;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import no.hvl.dat109.expoproject.utils.PasswordUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static no.hvl.dat109.expoproject.utils.PasswordUtils.validate;

@RestController
@RequestMapping("/api/user")
public class UserController implements IUserController {

    private final IUserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @Override
    @GetMapping
    public User getUser(@RequestParam(defaultValue = "") String username) {
        if (username.equals("")) {
            return null;
        }
        return us.getUser(username);
    }

    @Override
    @GetMapping("/all")
    public List<User> GetAllUsers() {
        return us.getAllUsers();
    }

    @Override
    @PostMapping("/login")
    public int postLogin(@RequestHeader String username, @RequestHeader String password) {
        User user = us.getUser(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "username or password is wrong");
        } else {
            String salt = user.getSalt();
            if (!validate(password, salt, user.getHash())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "username or password is wrong");
            } else {
                return user.getAccessLevel();
            }

        }
    }


}
