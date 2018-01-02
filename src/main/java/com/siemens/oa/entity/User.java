package com.siemens.oa.entity;


/**
 * \* Created with IntelliJ IDEA.
 * \* Description: User实体类
 * \* USER: xujin
 * \* Date: 2017/11/22
 * \* Time: 10:38
 * \
 */

public class User {
    private Integer userid;
    private String username;
    private String password;
    private String displayname;
    private String status;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", displayname='" + displayname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}