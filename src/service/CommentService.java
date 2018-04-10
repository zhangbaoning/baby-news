package service;

import entity.Comment;

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
     * 查询
     *
     * @param comment
     * @return
     */

    Comment selectComment(Comment comment);
}
