package com.siemens.oa.controller;

import com.siemens.oa.dao.ProjectDao;
import com.siemens.oa.entity.Project;
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
 * \* Time: 14:53
 * \
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectDao projectMapper;

    @GetMapping("/selectProjectByProjectID")
    public void selectProjectByProjectID(Integer PROJECTID) {
        projectMapper.selectProjectByProjectID(PROJECTID);
    }


    @PostMapping("/insertProject")
    public void insertProject(Project project) {
        projectMapper.insertProject(project);
    }
}