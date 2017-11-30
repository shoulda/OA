package com.siemens.oa.service;

import com.siemens.oa.entity.Project;

public interface ProjectService {
    void insertProject(Project project);

    void deleteProject(String projectname);

    void updateProject(Project project);

    Project selectProjectByProjectName(String projectname);
}
