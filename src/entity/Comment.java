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
    @Id
    private String id;
    private String content;
    private String userId;
    private Timestamp publishTime;
    private String articleId;

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
}
