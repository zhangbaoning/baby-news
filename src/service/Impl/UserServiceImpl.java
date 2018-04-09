package service.Impl;

import dao.UserDao;
import entity.User;

import java.util.List;

public class UserServiceImpl {
    UserDao userDao;
    public List<User> getUser(User user){
        List<User> userList= userDao.select(user);
        return userList;
    }
}
