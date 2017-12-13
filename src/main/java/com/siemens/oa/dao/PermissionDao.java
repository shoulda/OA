package com.siemens.oa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by gxurn9 on 12/13/2017.
 */
@Repository
@Mapper
public interface PermissionDao {
    /**
     * @param userid
     * @return
     */
    @Select("select auth from permission where userid =#{userid}")
    String selectAuthById(Integer userid);

    /**
     *
     * @param userid
     * @param auth
     */
    @Update("update permission set auth=#{auth} where userid =#{userid}")
    void updateAuthById(Integer userid, String auth);
}
