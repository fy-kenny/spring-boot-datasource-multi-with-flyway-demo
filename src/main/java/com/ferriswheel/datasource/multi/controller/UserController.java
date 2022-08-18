package com.ferriswheel.datasource.multi.controller;

import com.ferriswheel.datasource.multi.model.db1.User;
import com.ferriswheel.datasource.multi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kenny Fang
 * @since 0.0.1
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users")
    public List<User> findAll() {
        return this.userService.findAll();
    }
}
