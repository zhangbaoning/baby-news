package service.impl;

import dao.CommentDao;
import entity.Comment;
import org.springframework.stereotype.Service;
import service.CommentService;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private CommentDao commentDao;

    @Override
    public void insertComment(Comment comment) {
        commentDao.insert(comment);
    }

    /**
     * 删除
     *
     * @param comment
     */
    @Override
    public void deleteComment(Comment comment) {
        commentDao.delete(comment);

    }

    /**
     * 更新
     *
     * @param comment
     */
    @Override
    public void updateComment(Comment comment) {
        commentDao.updateByPrimaryKey(comment);

    }

    /**
     * 查询
     *
     * @param comment
     * @return
     */
    @Override
    public Comment selectComment(Comment comment) {
        commentDao.select(comment);
        return null;
    }

    /**
     * 查询所有的评论
     *
     * @return
     */
    @Override
    public List<Comment> selectAllComment() {
        List<Comment> commentList = commentDao.selectAll();
        return commentList;
    }
}
