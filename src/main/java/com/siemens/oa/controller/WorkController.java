package com.siemens.oa.controller;


import com.siemens.oa.entity.JsonListToWork2;
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
    /**
     * 自动装配（注入）
     */
    @Autowired
    private WorkService workService;
    @Autowired
    private UserService userService;

    /**
     * 插入work记录
     *
     * @param work
     */
    @PostMapping("/insertWork")
    public void insertWork(@RequestBody Work work) {
        workService.insertWork(work);
    }

    /**
     * 查询某人某周的工作记录
     *
     * @param session
     * @param weekid
     * @return
     */
    @RequestMapping("/selectWorkByScope")
    public JsonListToWork2 selectWorkByScope(HttpSession session, String weekid) {
        String username = session.getAttribute(WebSecurityConfig.SESSION_KEY).toString();
        List<Work> works = workService.selectWorkByWeekId(userService.selectUserIdByName(username), weekid);
        JsonListToWork2 jsonListToWork = workService.WorkToJson2(works, weekid);
//        System.out.println(jsonListToWork);
        return jsonListToWork;

    }

    /**
     * 保存数据
     *
     * @param object
     * @param session
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> modifyWork(@RequestBody String object, HttpSession session) {
        List<Work> work = workService.JsonToWork(object);
        System.out.println("----------收到" + work.size() + "前端work记录---------");

        List<Work> deleteWorks = workService.selectWorkByWeekId(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()), work.get(0).getWeekid());
        if (deleteWorks.size() > 0) {
            for (Work deleteWork : deleteWorks) {
                if (deleteWork.getM_STATUS().equals(1)) {
                    workService.deleteWork(deleteWork);
                    System.out.println("delete success");
                }
            }
        }
        if (work.size() != 0) {
            for (Work work1 : work) {
                work1.setUserid(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()));
                Work workTran = workService.selectWorkByUTPS(work1);
                System.out.println("对比数据库结果：" + workTran);
                if (workTran == null) {
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

    /**
     * 提交数据
     *
     * @param object
     * @param session
     * @return
     */
    @PostMapping("/submit")
    public Map<String, Object> subWork(@RequestBody String object, HttpSession session) {
        System.out.println("<************Submit************>");
        List<Work> work = workService.JsonToWork(object);
        System.out.println("----------收到" + work.size() + "前端work记录---------");

        List<Work> deleteWorks = workService.selectWorkByWeekId(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()), work.get(0).getWeekid());
        if (deleteWorks.size() > 0) {
            for (Work deleteWork : deleteWorks) {
                if (deleteWork.getM_STATUS().equals(1))
                    workService.deleteWork(deleteWork);
            }
        }
        if (work.size() != 0) {
            for (Work work1 : work) {
                System.out.println(work1);
                work1.setUserid(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()));
                Work workTran = workService.selectWorkByUTPS(work1);
                System.out.println("对比数据库结果：" + workTran);
                if (workTran == null) {
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
//
