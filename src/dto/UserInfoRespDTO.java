package dto;


import java.util.Date;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/7
 */
public class UserInfoRespDTO {
    /**
     * 网名
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 积分
     */
    private Integer score;
    /**
     * 发布活跃度
     */
    private Integer publishActivity;
    /**
     * 消息评论活跃度
     */
    private Integer commentMsgActivity;
    /**
     * 用户电话
     */
    private String mobile;
    /**
     * 用户注册时间
     */
    private Date dateJoined;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPublishActivity() {
        return publishActivity;
    }

    public void setPublishActivity(Integer publishActivity) {
        this.publishActivity = publishActivity;
    }

    public Integer getCommentMsgActivity() {
        return commentMsgActivity;
    }

    public void setCommentMsgActivity(Integer commentMsgActivity) {
        this.commentMsgActivity = commentMsgActivity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
}
