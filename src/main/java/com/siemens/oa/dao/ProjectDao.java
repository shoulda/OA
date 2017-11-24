package com.siemens.oa.dao;


import com.siemens.oa.entity.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProjectDao {
    /**
     * 根据projectid查询project
     *
     * @param PROJECTID
     * @return
     */
    @Select("select * from Project where PROJECTID = #{PROJECTID}")
    public Project selectProjectByProjectID(Integer PROJECTID);


    /**
     * 插入一条新project
     *
     * @param project
     */
    @Insert("insert into Project(PROJECTID,PROJECTNAME) values(#{PROJECTID},#{PROJECTNAME})")
    public void insertProject(Project project);

    /**
     * 修改project
     *
     * @param project
     */
    @Update("update Project set PROJECTNAME=#{PROJECTNAME} where PROJECTID=#{PROJECTID}")
    public void updateProject(Project project);

    /**
     * 根据projectID删除project
     *
     * @param PROJECTID
     */
    @Delete("delete from Project where PROJECTID = #{PROJECTID}")
    public void deleteProject(Integer PROJECTID);
}
