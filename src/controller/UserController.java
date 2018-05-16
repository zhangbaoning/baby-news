package controller;

import dto.UserDetailRespDTO;
import dto.UserInfoRespDTO;
import entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CommentService;
import service.MessageService;
import service.UserService;
import utils.RespStatusUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
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

    @Autowired
    private MessageService messageService;



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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register(@RequestBody User user) {
        Map<String, String> responseMap = new HashMap(2);
        int result = userService.insertUser(user);
        if (result == 1) {
//            注册成功
            responseMap.put(RespStatusUtils.STATUS, ResponseStatus.SUCCESS);
        } else {
//            注册失败
            responseMap.put(RespStatusUtils.STATUS, ResponseStatus.FAILURE);
        }
        return responseMap;
    }

    /**
     * 用户详情
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfoRespDTO getUserInfo(String userId) {
        User user = userService.getUserByPrimaryKey(userId);
//        通过userId得到评论总数
        int countComment = commentService.countByUserId(userId);
//        一条评论10个积分
        int commentScore = countComment * 10;
        int publishCount = messageService.countPublish(userId);
        int publishActivity = publishCount * 10;
        int commentOfMsgCount = commentService.countMsgByUserId(userId);
        int commentMsgActivity = commentOfMsgCount * 5;
        if (user != null) {
            UserInfoRespDTO userInfoRespDTO = new UserInfoRespDTO();
            BeanUtils.copyProperties(user, userInfoRespDTO);
            userInfoRespDTO.setDateJoined(user.getDateJoined());
            userInfoRespDTO.setScore(commentScore);
            userInfoRespDTO.setCommentCount(countComment);
            userInfoRespDTO.setPublishActivity(publishActivity);
            userInfoRespDTO.setCommentMsgActivity(commentMsgActivity);
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
            List<UserDetailRespDTO> dtoList = new LinkedList<>();
            if (userList != null) {
                for (User user : userList) {
                    UserDetailRespDTO respDTO = new UserDetailRespDTO();
                    BeanUtils.copyProperties(user, respDTO);
                    if ("1".equals(user.getRoleId())) {
                        respDTO.setRole("管理员");
                    } else if ("0".equals(user.getRoleId())) {
                        respDTO.setRole("普通用户");
                    }
                    dtoList.add(respDTO);
                }
                responseMap.put("userList", dtoList);
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
