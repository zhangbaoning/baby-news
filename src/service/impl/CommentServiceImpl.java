package service.impl;

import dao.CommentDao;
import entity.Comment;
import org.springframework.stereotype.Service;
import service.CommentService;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/10
 */
@Service
public class CommentServiceImpl implements CommentService {
    /**
     * 保存
     *
     * @param comment
     */
    private CommentDao commentDao;
    public void insertComment(Comment comment) {
        commentDao.insert(comment);
    }

    /**
     * 删除
     *
     * @param comment
     */
    public void deleteComment(Comment comment) {
        commentDao.delete(comment);

    }

    /**
     * 更新
     *
     * @param comment
     */
    public void updateComment(Comment comment) {
        commentDao.updateByPrimaryKey(comment);

    }

    /**
     * 查询
     *
     * @param comment
     * @return
     */
    public Comment selectComment(Comment comment) {
        commentDao.select(comment);
        return null;
    }
}