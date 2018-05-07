package dto;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/7
 */
public class UserInfoRespDTO {
    /**
     * 网名
     */
    private String nickName;
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
     * 总活跃度
     */
    private Integer totalActivity;
    /**
     * 今天活跃度
     */
    private Integer totdayActivity;

    public String getNickName() {
        return nickName;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Integer getTotalActivity() {
        return totalActivity;
    }

    public void setTotalActivity(Integer totalActivity) {
        this.totalActivity = totalActivity;
    }

    public Integer getTotdayActivity() {
        return totdayActivity;
    }

    public void setTotdayActivity(Integer totdayActivity) {
        this.totdayActivity = totdayActivity;
    }
}
