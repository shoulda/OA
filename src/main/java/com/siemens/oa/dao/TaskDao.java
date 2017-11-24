package com.siemens.oa.dao;


import com.siemens.oa.entity.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TaskDao {

    /**
     * 根据taskID查询task
     *
     * @param TASKID
     * @return
     */
    @Select("select * from Task where TASKID = #{TASKID}")
    public Task selectTaskByTaskID(Integer TASKID);


    /**
     * 插入一条新task
     *
     * @param task
     */
    @Insert("insert into Task(TASKID,TASKNAME) values(#{TASKID},#{TASKNAME})")
    public void insertTask(Task task);

    /**
     * 更新指定ID的task
     *
     * @param task
     */
    @Update("update Task set TASKNAME=#{TASKNAME} where TASKID=#{TASKID}")
    public void updateTask(Task task);

    /**
     * 删除指定ID的task
     *
     * @param TASKID
     */
    @Delete("delete from Task where TASKID = #{TASKID}")
    public void deleteTask(Integer TASKID);
}
