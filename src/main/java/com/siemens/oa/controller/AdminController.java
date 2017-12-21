package com.siemens.oa.controller;

import com.siemens.oa.annotation.AuthDetec;
import com.siemens.oa.entity.Project;
import com.siemens.oa.entity.Series;
import com.siemens.oa.entity.User;
import com.siemens.oa.entity.Work;
import com.siemens.oa.service.ProjectService;
import com.siemens.oa.service.UserService;
import com.siemens.oa.service.WorkService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/12/21
 * \* Time: 09:29
 * \
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private WorkService workService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/selectWorkSeries")
    @AuthDetec(authorities = "admin")
    public Series selectWorkSeries(Integer userid, String weekid, String weekConut) {
        Series series = workService.WorkToSeries(userid, weekid, weekConut);
        System.out.print(series);
        return series;
    }

    @GetMapping("/selectWorkByPW")
    @AuthDetec(authorities = "admin")
    public Series selectWorkByPW(Integer projectid, String weekid, String weekConut) {
        Series series = workService.ProjectToSeries(projectid, weekid, weekConut);
        System.out.print(series);
        return series;
    }

    @GetMapping("/selectAllWeekID")
    @AuthDetec(authorities = "admin")
    public List<String> selectAllWeekID() {
        return workService.selectAllWeekID();
    }

    @GetMapping("/selectWork")
    @AuthDetec(authorities = "admin")
    public List<Work> selectWork(HttpSession session) {
        int a = userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString());
        System.out.println(session.getAttribute(WebSecurityConfig.SESSION_KEY) + "-----SESSIONKEY-----" + a);
        return workService.selectWork();
    }

    /**
     * 根据weekid获取表格体数据
     *
     * @param weekid
     * @return
     */
    @GetMapping("/getTableData")
    @AuthDetec(authorities = "admin")
    public List getTableData(String weekid) {
        ArrayList<Object> tableData = new ArrayList<>();
        JSONObject userJsonAll = new JSONObject();
        List<User> userList = userService.selectAllUser();
        for (User user : userList) {
            List<Work> works = workService.selectOneWork(user.getUserid(), weekid);
            List<Project> projectList = projectService.selectAllProject();
            JSONObject userJson = new JSONObject();
            userJson.put("name", user.getUsername());
            userJsonAll.put("name", "总和");
            //初始化一个userjson
            for (Project project : projectList) {
                userJson.put(project.getProjectname(), 0);
            }
            for (Work work : works) {
                userJson.replace(projectService.selectProjectById(work.getProjectid()).getProjectname(), work.getHour());
            }
            tableData.add(userJson);
        }
        return tableData;
    }
}