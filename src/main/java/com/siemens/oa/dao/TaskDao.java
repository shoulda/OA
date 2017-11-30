package com.siemens.oa.dao;


import com.siemens.oa.entity.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TaskDao {

    /**
     * 插入一条新任务记录
     *
     * @param task
     */
    @Insert("insert into Task(taskid,taskname) values(#{taskid},#{taskname})")
    void insertTask(Task task);

    /**
     * 根据任务名删除任务
     *
     * @param TASKNAME
     */
    @Delete("delete from Task where taskname = #{taskname}")
    void deleteTask(String TASKNAME);

    /**
     * 根据任务ID更新任务
     *
     * @param task
     */
    @Update("update Task set taskname=#{taskname} where taskid=#{taskid}")
    void updateTask(Task task);

    /**
     * 根据任务名查询任务
     *
     * @param taskname
     * @return
     */
    @Select("select * from Task where taskname = #{taskname}")
    Task selectTaskByTaskName(String taskname);
}
