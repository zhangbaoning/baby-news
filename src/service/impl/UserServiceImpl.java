package service.impl;

import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public List<User> getUser(User user) {
        List<User> userList = userDao.select(user);
        return userList;
    }
//    public int  insertUser(User user){
//       return userDao.insert(user);
//    }

    /**
     * 保存
     *
     * @param user
     */
    public int insertUser(User user) {
        return userDao.insert(user);

    }

    /**
     * 删除
     *
     * @param user
     */
    public void deleteUser(User user) {
        userDao.delete(user);

    }

    /**
     * 更新
     *
     * @param user
     */
    public void updateUser(User user) {
        userDao.updateByPrimaryKey(user);
    }

    /**
     * 查询
     *
     * @param user
     * @return
     */
    public User selectUser(User user) {
//       return userDao.s;
        List<User> userList = null;
        try {
            userList = userDao.select(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList.get(0);
    }


}
