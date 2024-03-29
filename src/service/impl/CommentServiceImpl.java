package service.impl;

import dao.CommentDao;
import entity.Comment;
import org.springframework.stereotype.Service;
import service.CommentService;
import utils.UUIDUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
        comment.setId(UUIDUtils.getId());
        comment.setPublishTime(new Timestamp(System.currentTimeMillis()));
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
     * 通过主键去删除评论
     *
     * @param commentId
     */
    @Override
    public int deleteByPrimaykey(String commentId) {

        return commentDao.deleteByPrimaryKey(commentId);
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
     * 通过文章ID查询评论
     *
     * @param articleId 文章ID
     * @return
     */
    @Override
    public List<Comment> selectCommentsById(String articleId) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        List<Comment> commentList = commentDao.select(comment);
        return commentList;
    }

    /**
     * 通过消息ID查询评论
     *
     * @param messageId
     * @return
     */
    @Override
    public List<Comment> selectByMsgId(String messageId) {
        Comment comment = new Comment();
        comment.setMessageId(messageId);
        List<Comment> commentList = commentDao.select(comment);
        return commentList;
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

    /**
     * 通过userID查询出评论总数
     *
     * @param userId
     * @return
     */
    @Override
    public int countByUserId(String userId) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        int count = commentDao.selectCount(comment);
        return count;
    }

    /**
     * 计算消息的评论
     *
     * @param userId 用户ID
     * @return 消息的评论数
     */
    @Override
    public int countMsgByUserId(String userId) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setArticleId(null);
        int count = commentDao.selectCount(comment);
        return count;
    }
}
