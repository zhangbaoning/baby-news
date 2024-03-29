package service;

import entity.Comment;

import java.util.List;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/4/10
 */
public interface CommentService {
    /**
     * 保存
     *
     * @param comment
     */
    void insertComment(Comment comment);

    /**
     * 删除
     *
     * @param comment
     */
    void deleteComment(Comment comment);

    /**
     * 通过主键去删除评论
     *
     * @param commentId
     */
    int deleteByPrimaykey(String commentId);
    /**
     * 更新
     *
     * @param comment
     */

    void updateComment(Comment comment);

    /**
     * 通过文章ID查询评论
     *
     * @param articleId 文章ID
     * @return
     */

    List<Comment> selectCommentsById(String articleId);

    /**
     * 通过消息ID查询评论
     *
     * @param messageId
     * @return
     */
    List<Comment> selectByMsgId(String messageId);

    /**
     * 查询所有的评论
     *
     * @return
     */
    List<Comment> selectAllComment();

    /**
     * 通过userID查询出评论总数
     *
     * @param userId
     * @return 评论总数
     */
    int countByUserId(String userId);

    /**
     * 计算消息的评论
     *
     * @param userId 用户ID
     * @return 消息的评论数
     */
    int countMsgByUserId(String userId);
}
