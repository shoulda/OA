package com.siemens.oa.controller;

import com.siemens.oa.annotation.AuthDetec;
import com.siemens.oa.entity.User;
import com.siemens.oa.service.PermissionService;
import com.siemens.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gxurn9 on 11/22/2017.
 */
@Controller
public class LoginController {
    private final UserService userService;
    private final PermissionService permissionService;

    @Autowired
    public LoginController(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @GetMapping("/")
    public String beforeLogin() {
        return "login";
    }

//    @GetMapping("admin")
//    public String adminLogin() {
//        return "admin";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> loginPost(String userName, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.selectUserByName(userName);

        if (user != null) {
            if (password.equals(user.getPassword())) {
                session.setAttribute(WebSecurityConfig.SESSION_KEY, userName);
                // System.out.println(session.getAttribute(WebSecurityConfig.SESSION_KEY));
                map.put("success", true);
                map.put("message", "login in successful");
                map.put("code", "200");
//                if (permissionService.selectAuthById(user.getUserid()).equals("admin")) {
//                    map.put("auth", "admin");
//                    return map;
//                }
//                map.put("auth", "user");
//                System.out.println(map);
                return map;
            } else {
                map.put("success", false);
                map.put("message", "Username or password false");
                map.put("code", "403");
                return map;
            }
        } else {
            map.put("success", false);
            map.put("message", "User not exist!");
            map.put("code", "404");
            return map;
        }

    }

    /**
     * 测试查询一个人一周工作情况饼图
     *
     * @return
     */
    @GetMapping("/testOne")
    public String testOne() {
        return "testOne";
    }

    /**
     * 测试查询一个项目一周情况饼图
     *
     * @return
     */
    @GetMapping("/testProject")
    public String testProject() {
        return "testProject";
    }

    /**
     * 测试表格
     *
     * @return
     */
    @GetMapping("/testTimeSheetTable")
    public String testTimeSheetTable() {
        return "TimeSheetTable";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
//    @AuthDetec(authorities = "admin")
    public String admin() {
        return "Menulayout";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}
//