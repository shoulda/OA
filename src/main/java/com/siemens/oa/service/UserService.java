package com.siemens.oa.service;

import com.siemens.oa.entity.User;

public interface UserService {
    void insertUser(User user);

    void deleteUser(String username);

    void updateUser(User user);

    User selectUserByName(String username);

    Integer selectUserIdByName(String username);

    User selectUserById(Integer userid);
}
