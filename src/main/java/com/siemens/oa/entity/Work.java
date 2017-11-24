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
    private Integer WORKID;
    private Integer USERID;
    private Integer PROJECTID;
    private Integer TASKID;
    private Integer WEEKID;
    private Integer DAY;
    private Integer HOUR;
    private Integer M_STATUS;

    public Integer getDAY() {
        return DAY;
    }

    public void setDAY(Integer DAY) {
        this.DAY = DAY;
    }

    public Integer getHOUR() {
        return HOUR;
    }

    public void setHOUR(Integer HOUR) {
        this.HOUR = HOUR;
    }

    public Integer getM_STATUS() {
        return M_STATUS;
    }

    public void setM_STATUS(Integer m_STATUS) {
        M_STATUS = m_STATUS;
    }

    public Integer getWORKID() {
        return WORKID;
    }

    public void setWORKID(Integer WORKID) {
        this.WORKID = WORKID;
    }

    public Integer getUSERID() {
        return USERID;
    }

    public void setUSERID(Integer USERID) {
        this.USERID = USERID;
    }

    public Integer getPROJECTID() {
        return PROJECTID;
    }

    public void setPROJECTID(Integer PROJECTID) {
        this.PROJECTID = PROJECTID;
    }

    public Integer getTASKID() {
        return TASKID;
    }

    public void setTASKID(Integer TASKID) {
        this.TASKID = TASKID;
    }

    public Integer getWEEKID() {
        return WEEKID;
    }

    public void setWEEKID(Integer WEEKID) {
        this.WEEKID = WEEKID;
    }
}