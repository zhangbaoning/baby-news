package controller;

import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * @param user
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody User user) {
        Map responseMap = new HashMap();
        User loginUser = userService.selectUser(user);
        if (loginUser != null) {

//            登陆之后将UserID返回
            responseMap.put("user", loginUser.getId());
            responseMap.put("info", ResponseStatus.SUCCESS);
            return responseMap;
        } else {
            responseMap.put("info", ResponseStatus.FAILURE);
            return responseMap;
        }
    }

    public void register(User user) {
        Map responseMap = new HashMap(16);
        int info = userService.insertUser(user);

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Map getAll() {
        try {
            Map responseMap = new HashMap();
            List<User> userList = userService.getAll();
            if (userList != null) {
                responseMap.put("userList", userList);
                responseMap.put("info", ResponseStatus.SUCCESS);
                return responseMap;
            } else {
                responseMap.put("info", ResponseStatus.FAILURE);
                return responseMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
