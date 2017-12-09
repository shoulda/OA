package com.siemens.oa.dao;

import com.siemens.oa.entity.Work;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WorkDao {

    /**
     * 插入一条新工作记录
     *
     * @param work
     */
    @Insert("insert into Work(workid,userid,projectid,taskid,weekid,stamp,hour,m_STATUS) " +
            "values(#{workid},#{userid},#{projectid},#{taskid},#{weekid},#{stamp},#{hour},#{m_STATUS})")
    void insertWork(Work work);

    /**
     * 删除工作记录
     *
     * @param work
     */
    @Delete("delete from Work where userid=#{userid} and projectid=#{projectid} and taskid=#{taskid} and stamp=#{stamp}")
    void deleteWork(Work work);

    /**
     * 根据工作ID更新工作信息
     *
     * @param work
     */
    @Update("update Work set userid=#{userid}," +
            "projectid=#{projectid},taskid=#{taskid}," +
            "weekid=#{weekid},stamp=#{stamp} ,hour=#{hour},m_STATUS=#{m_STATUS} " +
            "where workid=#{workid}")
    void updateWork(Work work);

    /**
     * 查找所有工作记录
     *
     * @return
     */
    @Select("select * from Work")
    List<Work> selectWork();


    /**
     * 根据UTPS查找用户工作信息
     *
     * @param work
     * @return
     */
    @Select("select * from Work where userid=#{userid} and projectid=#{projectid} and taskid=#{taskid} and stamp=#{stamp}")
    Work selectWorkByUTPS(Work work);

    /**
     * 根据用户ID和查找指定范围工作记录
     *
     * @param userid
     * @return
     */
    @Select("select * from Work where userid=#{userid} and weekid=#{weekid}")
    List<Work> selectWorkByWeekId(@Param("userid") Integer userid,
                                  @Param("weekid") String weekid);

}
