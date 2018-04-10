package service.impl;

import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class UserServiceImpl {
    UserDao userDao;
    public List<User> getUser(User user){
        List<User> userList= userDao.select(user);
        return userList;
    }
    public int  insertUser(User user){
       return userDao.insert(user);
    }
}
