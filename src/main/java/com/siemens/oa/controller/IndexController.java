package com.siemens.oa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gxurn9 on 11/23/2017.
 */
@RestController
public class IndexController {

    @PostMapping("/login")
    public Map<String,Object> loginPost(String userName, String password, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if (!(("admin".equals(userName))&&"admin".equals(password))){
            map.put("success",false);
            map.put("message","Username or password false");
            map.put("code","403");
            return map;
        }

        session.setAttribute(WebSecurityConfig.SESSION_KEY,userName);

        map.put("success",true);
        map.put("message","login in successful");
        map.put("code","200");
        return map;




    }
}
