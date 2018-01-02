package com.siemens.oa.service;

import com.siemens.oa.dao.ProjectDao;
import com.siemens.oa.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/30
 * \* Time: 14:49
 * \
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * 插入一条新工程记录
     *
     * @param project
     */
    @Override
    public void insertProject(Project project) {
        projectDao.insertProject(project);
    }

    /**
     * 根据工程名删除工程
     *
     * @param projectname
     */
    @Override
    public void deleteProject(String projectname) {
        projectDao.deleteProject(projectname);
    }

    /**
     * 根据工程ID更新工程
     *
     * @param project
     */
    @Override
    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }

    /**
     * 根据工程名查询工程
     *
     * @param projectname
     * @return
     */
    @Override
    public Project selectProjectByProjectName(String projectname) {
        return projectDao.selectProjectByProjectName(projectname);
    }

    /**
     * 根据工程ID查询工程
     *
     * @param projectid
     * @return
     */
    @Override
    public Project selectProjectById(int projectid) {
        return projectDao.selectProjectById(projectid);
    }

    /**
     * 查询所有工程
     *
     * @return
     */
    @Override
    public List<Project> selectAllProject() {
        return projectDao.selectAllProject();
    }
}