package com.siemens.oa.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 14:48
 * \
 */
public class Task {
    private Integer TASKID;
    private String TASKNAME;

    public Integer getTASKID() {
        return TASKID;
    }

    public void setTASKID(Integer TASKID) {
        this.TASKID = TASKID;
    }

    public String getTASKNAME() {
        return TASKNAME;
    }

    public void setTASKNAME(String TASKNAME) {
        this.TASKNAME = TASKNAME;
    }
}