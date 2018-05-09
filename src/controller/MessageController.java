package controller;

import dto.MessageRespDTO;
import entity.Message;
import entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MessageService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/8
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "getAllMessage", method = RequestMethod.GET)
    @ResponseBody
    public List<MessageRespDTO> getAll() {
        List<Message> messageList = messageService.getAll();
        List<MessageRespDTO> messageRespDTOList = new ArrayList<>();
        for (Message message : messageList) {
            MessageRespDTO messageRespDTO = new MessageRespDTO();
            User user = userService.getUserByPrimaryKey(message.getUserId());
            BeanUtils.copyProperties(message, messageRespDTO);
            messageRespDTO.setAvatar(user.getAvatar());
            messageRespDTO.setNickname(user.getNickname());
            messageRespDTOList.add(messageRespDTO);
        }
        return messageRespDTOList;
    }

    @RequestMapping(value = "getMessage", method = RequestMethod.GET)
    @ResponseBody
    public MessageRespDTO getMessageById(@RequestParam String id) {

//        通过ID查询到消息详情
        Message message = messageService.getById(id);

//        通过消息的外键查询到用户信息
        User user = userService.getUserByPrimaryKey(message.getUserId());

//        回复消息传输对象
        MessageRespDTO messageRespDTO = new MessageRespDTO();
//        复制相同属性的值
        BeanUtils.copyProperties(message, messageRespDTO);
        messageRespDTO.setAvatar(user.getAvatar());
        messageRespDTO.setNickname(user.getNickname());

        return messageRespDTO;
    }
}
