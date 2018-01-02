package com.siemens.oa.service;

import com.siemens.oa.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gxurn9 on 12/13/2017.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private final PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public String selectAuthById(Integer userid) {
        return permissionDao.selectAuthById(userid);
    }

    @Override
    public void updateAuthById(Integer userid, String auth) {
        permissionDao.updateAuthById(userid, auth);
    }
}
