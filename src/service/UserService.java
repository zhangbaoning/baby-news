package service;

import entity.User;

import java.util.List;

public interface UserService {
    /**
     *
     * @param user
     * @return
     */
    public List<User> getUser(User user);

}
