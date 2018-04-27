package dto;

import java.sql.Timestamp;

/**
 * @Description 评论的响应消息
 * @Author zhangbaoning
 * @Date 2018/4/27
 */
public class CommentRespDTO {
    /**
     * 评论ID
     */
    private String id;
    /**
     * 文章名
     */
    private String article;

    /**
     * 评论
     */
    private String comment;

    /**
     * 网名
     */
    private String nickname;

    /**
     * 发布时间
     */
    private Timestamp publishTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
}
