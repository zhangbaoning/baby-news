package service.impl;

import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Service;
import service.UserService;
import tk.mybatis.mapper.entity.Example;
import utils.UUIDUtils;

import javax.annotation.Resource;
import java.util.Date;
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
    @Override
    public int insertUser(User user) {
//        生成用户编号
        user.setId(UUIDUtils.getId());
//        生成注册时间为当前时间
        user.setDateJoined(new Date());
//        只插入不为NULL的值，由数据库默认值自动补全空值
        return userDao.insertSelective(user);

    }

    /**
     * 删除
     *
     * @param user
     */
    @Override
    public int deleteUser(User user) {
        return userDao.delete(user);

    }

    /**
     * 更新
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userDao.updateByPrimaryKey(user);
    }

    /**
     * 查询
     *
     * @param user
     * @return
     */
    @Override
    public User selectUser(User user) {
        List<User> userList = null;
        try {
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", user.getUsername()).andEqualTo("password", user.getPassword());
            userList = userDao.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userList.size() > 0 && userList != null) {
            return userList.get(0);

        } else {
            return null;
        }
    }

    /**
     * 获取到全部用户
     *
     * @return 用户集合
     */
    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

    @Override
    public User getUserByPrimaryKey(String userId) {
        User user = new User();
        user.setId(userId);
        User userResult = userDao.selectByPrimaryKey(user);
        return userResult;
    }


}
