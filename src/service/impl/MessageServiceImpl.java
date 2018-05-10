package service.impl;

import dao.MessageDao;
import entity.Message;
import org.springframework.stereotype.Service;
import service.MessageService;
import utils.UUIDUtils;

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

    /**
     * 通过id查询到消息
     *
     * @param id
     * @return
     */
    @Override
    public Message getById(String id) {
        Message message = new Message();
        message.setId(id);
        Message result = messageDao.selectByPrimaryKey(message);
        return result;
    }

    /**
     * 添加消息
     *
     * @param message
     */
    @Override
    public int add(Message message) {
        message.setId(UUIDUtils.getId());
        return messageDao.insert(message);
    }

    /**
     * 计算发布消息数
     *
     * @param userId 用户ID
     * @return 发布消息数
     */
    @Override
    public int countPublish(String userId) {
        Message message = new Message();
        message.setUserId(userId);
        int count = messageDao.selectCount(message);
        return count;
    }
}
