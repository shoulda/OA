package com.siemens.oa.service;

import com.siemens.oa.entity.Project;
import com.siemens.oa.entity.Work;

import java.util.List;

public interface ProjectService {
    void insertProject(Project project);

    void deleteProject(String projectname);

    void updateProject(Project project);

    Project selectProjectByProjectName(String projectname);

    Project selectProjectById(int projectid);

    List<Project> selectAllProject();

}
