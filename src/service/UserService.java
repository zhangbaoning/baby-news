package service;

import entity.User;

import java.util.List;

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

    /**
     * 获取到全部用户
     *
     * @return 用户集合
     */
    List<User> getAll();

    /**
     * 通过主键进行查询
     *
     * @return
     */
    User getUserByPrimaryKey(String id);
}
