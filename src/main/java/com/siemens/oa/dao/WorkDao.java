package com.siemens.oa.dao;

import com.siemens.oa.entity.Work;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WorkDao {

    /**
     * 根据useID、start、end时间来查找条目
     *
     * @param USERID
     * @param start
     * @param end
     * @return
     */
    @Select("select * from WORK where USERID=#{USERID} and STAMP between #{start} and #{end}")
    public List<Work> selectWorkByScope(@Param("USERID") Integer USERID,
                                        @Param("start") String start,
                                        @Param("end") String end);

    /**
     * 插入一条新work记录
     *
     * @param work
     */
    @Insert("insert into WORK(WORKID,USERID,PROJECTID,TASKID,STAMP,HOUR,M_STATUS) " +
            "values(#{WORKID},#{USERID},#{PROJECTID},#{TASKID},#{STAMP},#{HOUR},#{M_STATUS})")
    public void insertWork(Work work);

    /**
     * 更新指定workID的记录
     *
     * @param work
     */
    @Update("update WORK set " +
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
