package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/8
 */
@Entity
public class Message {
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 发布的人
     */
    private String userId;
    /**
     * 发布时间
     */
    private Timestamp publishTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(content, message.content) &&
                Objects.equals(userId, message.userId) &&
                Objects.equals(publishTime, message.publishTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, content, userId, publishTime);
    }

    @Column(name = "id", nullable = false)

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "publish_time", nullable = false)
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
}
