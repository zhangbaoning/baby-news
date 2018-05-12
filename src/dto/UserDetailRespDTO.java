package dto;

import entity.User;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/12
 */
public class UserDetailRespDTO extends User {
    /**
     * 角色名字
     */
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
