package controller;

import entity.User;
import org.springframework.stereotype.Controller;
import service.UserService;
@Controller
public class UserController {
    UserService userService;
    public void login(User user){
        userService.selectUser(user);
    }
    public void register(User user){
        userService.insertUser(user);
    }
}
