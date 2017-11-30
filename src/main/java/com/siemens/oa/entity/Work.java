package com.siemens.oa.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 15:00
 * \
 */
public class Work {
    private Integer workid;
    private Integer userid;
    private Integer projectid;
    private Integer taskid;
    private String stamp;
    private Integer hour;
    private Integer m_STATUS;

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getM_STATUS() {
        return m_STATUS;
    }

    public void setM_STATUS(Integer m_STATUS) {
        this.m_STATUS = m_STATUS;
    }

    @Override
    public String toString() {
        return "Work{" +
                "workid=" + workid +
                ", userid=" + userid +
                ", projectid=" + projectid +
                ", taskid=" + taskid +
                ", stamp='" + stamp + '\'' +
                ", hour=" + hour +
                ", m_STATUS=" + m_STATUS +
                '}';
    }
}