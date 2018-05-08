package dto;

import java.sql.Timestamp;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/8
 */
public class MessageRespDTO {
    /**
     * 主键
     */
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
    /**
     * 网名
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
