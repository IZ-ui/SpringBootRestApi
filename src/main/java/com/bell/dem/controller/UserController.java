package com.bell.dem.controller;

import com.bell.dem.service.UserService;
import com.bell.dem.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller для User
 */
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для маппинга /list
     */
    @PostMapping("/list")
    public List<UserShortOutView> getUserByFilter(@Validated(UserInView.List.class)
                                                  @RequestBody UserInView userInView) {
        return userService.getByFilter(userInView);
    }

    /**
     * Метод для маппинга /{id}
     */
    @GetMapping(value = {"/{id}"}, produces = APPLICATION_JSON_VALUE)
    public UserOutView getUserById(@PathVariable(name = "id") Integer id) {
        return userService.getByID(id);
    }

    /**
     * Метод для маппинга /update
     */
    @PostMapping(value = {"/update"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody @Validated(UserInView.Update.class) UserInView userInView) {
        userService.update(userInView);
    }

    /**
     * Метод для маппинга /save
     */
    @PostMapping("/save")
    public void createUser(@Validated(UserInView.Save.class) @RequestBody UserInView userInView) {
        userService.save(userInView);
    }
}
