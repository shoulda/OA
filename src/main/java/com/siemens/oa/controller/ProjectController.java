package com.siemens.oa.controller;

import com.siemens.oa.annotation.AuthDetec;
import com.siemens.oa.entity.Project;
import com.siemens.oa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: projectController
 * \* User: xujin
 * \* Date: 2017/12/10
 * \* Time: 11:10
 * \
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * 从project表获取所有project
     *
     * @return
     */
    @GetMapping("/getAllProject")
    public List<Project> selectAllProject() {
        return projectService.selectAllProject();
    }

    @PostMapping("/insertProject")
    @AuthDetec(authorities = "admin")
//    public void insertProject(String projectname){
//        Project project = new Project();
//        project.setProjectname(projectname);
//        System.out.println(project);
//        projectService.insertProject(project);
//    }
    public void insertProject(Project project){
        projectService.insertProject(project);
    }
}