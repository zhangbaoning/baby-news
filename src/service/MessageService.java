package service;

import entity.Message;

import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/8
 */
public interface MessageService {
    /**
     * 获得全部的社区消息
     *
     * @return 全部消息集合
     */
    List<Message> getAll();
}
