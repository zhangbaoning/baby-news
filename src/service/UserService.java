package service;

import entity.User;

public interface UserService {
    /**
     * 保存
     *
     * @param user
     */
    int insertUser(User user);

    /**
     * 删除
     *
     * @param user
     */
    void deleteUser(User user);

    /**
     * 更新
     *
     * @param user
     */

    void updateUser(User user);

    /**
     * 查询
     *
     * @param user
     * @return
     */

    User selectUser(User user);

}
