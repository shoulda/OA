package com.siemens.oa.dao;

import com.siemens.oa.entity.Work;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WorkDao {

    /**
     * 根据userID和weekID查询workID
     *
     * @param weekid
     * @return
     */
    @Select("select WORKID from Work where WEEKID=#{WEEKID} and USERID=#{USERID}")
    public List<Work> selectWorkByUW(Integer weekid);

    /**
     * 插入一条新work记录
     *
     * @param work
     */
    @Insert("insert into Work(USERID,PROJECTID,TASKID,WEEKID,DAY,HOUR,M_STATUS) " +
            "values(#{USERID},#{PROJECTID},#{TASKID},#{WEEKID},#{DAY},#{HOUR},#{M_STATUS})")
    public void insertWork(Work work);

    /**
     * 更新指定workID的记录
     *
     * @param work
     */
    @Update("update Work set " +
            "USERID=#{USERID},PROJECTID=#{PROJECTID}," +
            "TASKID=#{TASKID},WEEKID=#{WEEKID}, DAY=#{DAY}," +
            "HOUR=#{HOUR},M_STATUS=#{M_STATUS}" +
            "where WORKID=#{WORKID}")
    public void updateWork(Work work);

    /**
     * 删除指定workID的记录
     *
     * @param WORKID
     */
    @Delete("delete from Work where WORKID = #{WORKID}")
    public void deleteWork(Integer WORKID);
}
