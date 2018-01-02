package com.siemens.oa.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: Project实体类
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 14:48
 * \
 */
public class Project {
    private Integer projectid;
    private String projectname;

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectid=" + projectid +
                ", projectname='" + projectname + '\'' +
                '}';
    }
}
