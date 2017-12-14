package com.siemens.oa.service;

/**
 * Created by gxurn9 on 12/13/2017.
 */
public interface PermissionService {

    String selectAuthById(Integer userid);

    void updateAuthById(Integer userid,String auth);
}
