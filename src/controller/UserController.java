package controller;

import entity.User;
import service.UserService;

public class UserController {
    UserService userService;
    public void login(User user){
        userService.getUser(user);
    }
}
