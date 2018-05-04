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
     * 查询所有的评论
     *
     * @return
     */
    List<Comment> selectAllComment();
}
