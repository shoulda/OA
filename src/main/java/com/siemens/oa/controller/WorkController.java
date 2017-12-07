package com.siemens.oa.controller;


import com.siemens.oa.entity.Work;
import com.siemens.oa.service.UserService;
import com.siemens.oa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 15:01
 * \
 */
@RestController
@RequestMapping("/work")
public class WorkController {
    @Autowired
    private WorkService workService;
    @Autowired
    private UserService userService;

    @PostMapping("/insertWork")
    public void insertWork(@RequestBody Work work) {
        workService.insertWork(work);
    }

    @RequestMapping("/selectWorkByScope")
    public List<Work> selectWorkByScope(Integer userid, String start, String end) {
        List<Work> works = workService.selectWorkByScope(userid, start, end);
        System.out.println(works);
        return works;
    }

    @GetMapping("/selectWork")
    public List<Work> selectWork(HttpSession session) {
        int a = userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString());
        System.out.println(session.getAttribute(WebSecurityConfig.SESSION_KEY) + "-----SESSIONKEY-----" + a);
        return workService.selectWork();
    }


    @PostMapping("/save")
    public void modifyWork(@RequestBody String object, HttpSession session) {
        List<Work> work = workService.JsonToWork(object);
//        System.out.println(work);
        for (Work work1 : work) {
            work1.setUserid(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()));
//            System.out.println(work1);
//            System.out.println(workService.selectWorkByUTPS(work1));
            if (workService.selectWorkByUTPS(work1) != null && work1.getM_STATUS() == 1) {
                work1.setWorkid(workService.selectWorkByUTPS(work1).getWorkid());
//                System.out.println(work1);
                workService.updateWork(work1);
            } else if (workService.selectWorkByUTPS(work1) == null) {
                work1.setM_STATUS(1);
                workService.insertWork(work1);
            } else ;
        }
    }

    @PostMapping("/submit")
    public void subWork(@RequestBody String object, HttpSession session) {
        List<Work> work = workService.JsonToWork(object);
        for (Work work1 : work) {
            work1.setUserid(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()));
            if (workService.selectWorkByUTPS(work1) != null && work1.getM_STATUS() == 1) {
                work1.setM_STATUS(0);
                work1.setWorkid(workService.selectWorkByUTPS(work1).getWorkid());
                workService.updateWork(work1);
            } else if (workService.selectWorkByUTPS(work1) == null) {
                work1.setM_STATUS(0);
                workService.insertWork(work1);
            } else ;
        }
    }

}
