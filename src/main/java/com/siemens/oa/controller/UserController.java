package com.siemens.oa.controller;


import com.siemens.oa.dao.UserDao;
import com.siemens.oa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 11:20
 * \
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userMapper;

    /**
     * 根据ID查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/selectUserById")
    public User selectUserById(String id) {
        return userMapper.selectUserById(Integer.parseInt(id));
    }

    /**
     * 根据username查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/selectUserByName")
    public User selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    /**
     * 添加一个用户
     *
     * @param user
     */
    @PostMapping(value = "/addUser")
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @PostMapping(value = "/updateUser")
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 删除一个用户
     *
     * @param id
     */
    @PostMapping(value = "/deleteUser")
    public void deleteUser(String id) {
        userMapper.deleteUser(Integer.parseInt(id));
    }

    /**
     * 清空数据库
     */
    @PostMapping(value = "/deleteAll")
    public void deleteAll() {
        userMapper.deleteAll();
    }

}