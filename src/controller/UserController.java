package controller;

import dto.UserInfoRespDTO;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CommentService;
import service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/7
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private CommentService commentService;

    /**
     * @param user
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody User user) {
        Map responseMap = new HashMap(16);
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

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfoRespDTO getUserInfo(String userId) {
        User user = userService.getUserByPrimaryKey(userId);
//        通过userId得到评论总数
        int countComment = commentService.countByUserId(userId);
//        一条评论10个积分
        int score = countComment * 10;
        if (user != null) {
            UserInfoRespDTO userInfoRespDTO = new UserInfoRespDTO();
            userInfoRespDTO.setNickName(user.getNickname());
            userInfoRespDTO.setAvatar(user.getAvatar());
            userInfoRespDTO.setScore(score);
            userInfoRespDTO.setCommentCount(countComment);
            // TODO 活跃度
            return userInfoRespDTO;
        } else {
            // TODO 未查到处理
            return null;
        }

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
