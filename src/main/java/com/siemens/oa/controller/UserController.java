package com.siemens.oa.controller;

import com.siemens.oa.entity.User;
import com.siemens.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/12/19
 * \* Time: 15:48
 * \
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.selectAllUser();
    }

    @PostMapping("/modifyPassword")
    public Map<String, Object> modifyPassword(HttpSession session, String oldPassword, String newPassword1, String newPassword2) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.selectUserByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString());

        if (!newPassword1.equals(newPassword2)) {
            map.put("code", 403);
            map.put("message", "two new password is not same!");
            map.put("success", false);
            return map;
        }

        if (oldPassword.equals(user.getPassword())) {
            map.put("code", 200);
            map.put("message", "modify password successful!");
            map.put("success", true);
            user.setPassword(newPassword1);
            userService.updateUser(user);
            return map;
        } else {
            map.put("code", 403);
            map.put("message", "modify password failed!");
            map.put("success", false);
            return map;
        }
    }
}
