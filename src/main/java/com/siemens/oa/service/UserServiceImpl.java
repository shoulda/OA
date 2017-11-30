package com.siemens.oa.service;

import com.siemens.oa.dao.UserDao;
import com.siemens.oa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: userService接口的实现类
 * \* User: xujin
 * \* Date: 2017/11/30
 * \* Time: 10:25
 * \
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 插入一条新用户记录
     *
     * @param user
     */
    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    /**
     * 根据用户名删除用户
     *
     * @param username
     */
    @Override
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

    /**
     * 根据用户ID更新用户
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User selectUserByName(String username) {
        return userDao.selectUserByName(username);
    }
}