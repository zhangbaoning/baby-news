package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/7
 */
@Entity
public class Comment {
    /**
     * 评论主键
     */
    @Id
    private String id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 发布时间
     */
    private Timestamp publishTime;
    /**
     * 用户外键
     */
    private String userId;
    /**
     * 文章外键
     */
    private String articleId;
    /**
     * 消息外键
     */
    private String messageId;

    @Basic
    @Column(name = "id", nullable = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Basic
    @Column(name = "user_id", nullable = true, length = 255)

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "publish_time", nullable = true)

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }


    @Basic
    @Column(name = "article_id", nullable = true)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "message_id", nullable = true)
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
