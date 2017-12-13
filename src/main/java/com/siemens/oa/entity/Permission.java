package com.siemens.oa.entity;

/**
 * Created by gxurn9 on 12/13/2017.
 */
public class Permission {
    private Integer userid;
    private String auth;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
