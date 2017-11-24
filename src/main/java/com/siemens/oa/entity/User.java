package com.siemens.oa.entity;


/**
 * \* Created with IntelliJ IDEA.
 * \* Description: user实体类
 * \* USER: xujin
 * \* Date: 2017/11/22
 * \* Time: 10:38
 * \
 */

public class User {
    private Integer USERID;
    private String USERNAME;
    private String PASSWORD;
    private String DISPLAYNAME;

    public String getDISPLAYNAME() {
        return DISPLAYNAME;
    }

    public void setDISPLAYNAME(String DISPLAYNAME) {
        this.DISPLAYNAME = DISPLAYNAME;
    }

    private String STATUS;

    public Integer getUSERID() {
        return USERID;
    }

    public void setUSERID(Integer USERID) {
        this.USERID = USERID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}