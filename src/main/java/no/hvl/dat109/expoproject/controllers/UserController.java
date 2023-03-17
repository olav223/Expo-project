package no.hvl.dat109.expoproject.controllers;

import no.hvl.dat109.expoproject.database.UserService;
import no.hvl.dat109.expoproject.entities.User;
import no.hvl.dat109.expoproject.interfaces.controllers.IUserController;
import no.hvl.dat109.expoproject.interfaces.database.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController implements IUserController {

    private final IUserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    @Override
    @GetMapping
    public User getUser(String username) {
        return null;
    }

    @Override
    @GetMapping("/all")
    public List<User> GetAllUsers() {
        return null;
    }

    @Override
    @PostMapping("/login")
    public int postLogin(String username, String password) {
        return 0;
    }
}