package com.siemens.oa.controller;


import com.siemens.oa.entity.JsonListToWork;
import com.siemens.oa.entity.Series;
import com.siemens.oa.entity.Work;
import com.siemens.oa.service.UserService;
import com.siemens.oa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/selectWorkSeries")
    public Series selectWorkSeries(Integer userid, String weekid, Integer weekConut) {
        System.out.print(userid + "-----" + weekid + "-----" + weekConut + "\n");
        Series series = workService.WorkToSeries(userid, weekid, weekConut);
        System.out.print(series);
        return series;
    }

    @RequestMapping("/selectWorkByScope")
    public JsonListToWork selectWorkByScope(HttpSession session, String weekId) {
        String username = session.getAttribute(WebSecurityConfig.SESSION_KEY).toString();
        List<Work> works = workService.selectWorkByWeekId(userService.selectUserIdByName(username), weekId);
        JsonListToWork jsonListToWork = workService.WorkToJson(works, weekId);
//        JsonListToWork jsonListToWork = workService.WorkToJson2(works, weekId);
        System.out.println(jsonListToWork);
        return jsonListToWork;

    }

    @GetMapping("/selectWork")
    public List<Work> selectWork(HttpSession session) {
        int a = userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString());
        System.out.println(session.getAttribute(WebSecurityConfig.SESSION_KEY) + "-----SESSIONKEY-----" + a);
        return workService.selectWork();
    }


    @PostMapping("/save")
    public Map<String, Object> modifyWork(@RequestBody String object, HttpSession session) {
        System.out.println("<************Save************>");
        List<Work> work = workService.JsonToWork(object);
        System.out.println("----------收到" + work.size() + "前端work记录---------");
        if (work.size() != 0) {
            for (Work work1 : work) {
                work1.setUserid(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()));
                Work workTran = workService.selectWorkByUTPS(work1);
                System.out.println("对比数据库结果：" + workTran);
                if (workTran != null && workTran.getM_STATUS().equals(1)) {
                    work1.setWorkid(workTran.getWorkid());
                    work1.setM_STATUS(1);
                    workService.updateWork(work1);
                    System.out.println("<*****>此条数据已更新,projectId:" + work1.getProjectid() + ", taskId:" + work1.getTaskid());
                } else if (workTran == null) {
                    work1.setM_STATUS(1);
                    workService.insertWork(work1);
                    System.out.println("<*****>此条数据已插入,projectId:" + work1.getProjectid() + ", taskId:" + work1.getTaskid());
                } else ;
            }
            return workService.SubStatus(true, 200, "Save Success!");
        } else {
            return workService.SubStatus(false, 404, "Save Failed! Message is null!");
        }

    }

    @PostMapping("/submit")
    public Map<String, Object> subWork(@RequestBody String object, HttpSession session) {
        System.out.println("<************Submit************>");
        List<Work> work = workService.JsonToWork(object);
        System.out.println("----------收到" + work.size() + "前端work记录---------");
        if (work.size() != 0) {
            for (Work work1 : work) {
                System.out.println(work1);
                work1.setUserid(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()));
                Work workTran = workService.selectWorkByUTPS(work1);
                System.out.println("对比数据库结果：" + workTran);
                if (workTran != null && workTran.getM_STATUS().equals(1)) {
                    work1.setM_STATUS(0);
                    work1.setWorkid(workTran.getWorkid());
                    workService.updateWork(work1);
                    System.out.println("<*****>此条数据已更新,projectId:" + work1.getProjectid() + ", taskId:" + work1.getTaskid());
                } else if (workTran == null) {
                    work1.setM_STATUS(0);
                    workService.insertWork(work1);
                    System.out.println("<*****>此条数据已插入,projectId:" + work1.getProjectid() + ", taskId:" + work1.getTaskid());
                } else ;
            }
            return workService.SubStatus(true, 200, "Submit Success!");
        } else {
            return workService.SubStatus(false, 404, "Submit Failed! Message is null!");
        }

    }

}
