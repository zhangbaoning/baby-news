package service.impl;

import dao.MessageDao;
import entity.Message;
import org.springframework.stereotype.Service;
import service.MessageService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/8
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    /**
     * 获得全部的社区消息
     *
     * @return 全部消息集合
     */
    @Override
    public List<Message> getAll() {
        List<Message> messageList = messageDao.selectAll();
        return messageList;
    }
}
