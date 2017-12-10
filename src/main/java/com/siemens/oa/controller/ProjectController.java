package com.siemens.oa.controller;

import com.siemens.oa.entity.Project;
import com.siemens.oa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getProject")
    public List<Project> selectAllProject() {
        return projectService.selectAllProject();
    }
}