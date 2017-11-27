package com.siemens.oa.dao;

import com.siemens.oa.entity.Work;
import org.apache.ibatis.annotations.*;
import org.json.simple.JSONObject;
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
    @Select("select WORKID from WORK where WEEKID=#{WEEKID} and USERID=#{USERID}")
    public List<Work> selectWorkByUW(Integer weekid);

    /**
     * 根据useID、start、end时间来查找条目
     *
     * @param userid
     * @param start
     * @param end
     * @return
     */
    @Select("select * from WORK where USERID=#{USERID} and DAY>=#{start} and DAY<=#{end}")
    public List<Work> selectWorkByScope(Integer userid, String start, String end);

    /**
     * 插入一条新work记录
     *
     * @param work
     */
    @Insert("insert into WORK(USERID,PROJECTID,TASKID,STAMP,HOUR,M_STATUS) " +
            "values(#{USERID},#{PROJECTID},#{TASKID},#{STAMP},#{HOUR},#{M_STATUS})")
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

    public boolean saveToDB(JSONObject object);

    public JSONObject getFromDB(String userid, String start, String end);
}
