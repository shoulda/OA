package com.siemens.oa.dao;


import com.siemens.oa.entity.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectDao {

    /**
     * 插入一条新工程记录
     *
     * @param project
     */
    @Insert("insert into Project(projectid,projectname) values(#{projectid},#{projectname})")
    void insertProject(Project project);

    /**
     * 根据工程名删除工程
     *
     * @param projectname
     */
    @Delete("delete from Project where projectname = #{projectname}")
    void deleteProject(String projectname);

    /**
     * 根据工程ID更新工程
     *
     * @param project
     */
    @Update("update Project set projectname=#{projectname} where projectid=#{projectid}")
    void updateProject(Project project);

    /**
     * 根据工程名查询工程
     *
     * @param projectname
     * @return
     */
    @Select("select * from Project where projectname = #{projectname}")
    Project selectProjectByProjectName(String projectname);

    /**
     * 根据id查找project条目
     *
     * @param projectid
     * @return
     */
    @Select("select * from Project where projectid = #{projectid}")
    Project selectProjectById(int projectid);

    /**
     * 查询所有的project
     *
     * @return
     */
    @Select("select * from Project")
    List<Project> selectAllProject();
}
