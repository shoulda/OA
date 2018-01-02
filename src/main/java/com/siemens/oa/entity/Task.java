package com.siemens.oa.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: Task实体类
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 14:48
 * \
 */
public class Task {
    private Integer taskid;
    private String taskname;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", taskname='" + taskname + '\'' +
                '}';
    }
}