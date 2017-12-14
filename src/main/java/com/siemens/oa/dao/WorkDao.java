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

    /**
     * 根据userID和weekID查询该用户一周内完成任务的工作记录并按user和task进行分组合并,
     * 也就是相当于当查询出userID与taskID都相同的多列时会进行合并，他们的hour会相加
     * 用于构建某人一周的工作耗时饼状图
     *
     * @param userid
     * @param weekid
     * @return
     */
    @Select("select projectid,taskid,sum(Hour) as hour from Work where userid=#{userid} and weekid=#{weekid} GROUP BY projectid,taskid")
    List<Work> selectWorkByUW(@Param("userid") Integer userid,
                              @Param("weekid") String weekid);

    /**
     * 根据projectID和weekID查询该项目的参与者一周内工作记录并按project和task进行分组合并,
     * 也就是相当于当查询出projectID与taskID都相同的多列时会进行合并，他们的hour会相加
     * 用于构建某工程一周人员耗时的饼状图
     *
     * @param projectid
     * @param weekid
     * @return
     */
    @Select("select userid,taskid,sum(Hour) as hour from Work where weekid = #{weekid} and projectid=#{projectid} GROUP BY userid, taskid")
    List<Work> selectWorkByPW(@Param("projectid") Integer projectid, @Param("weekid") String weekid);

    /**
     * 根据userID和weekID查询该用户这一周内工作记录并按project进行分组合并(不需要细化到task),
     * 也就是相当于当查询出projectID与taskID都相同的多列时会进行合并，他们的hour会相加
     * 用于构建报表
     *
     * @param userid
     * @param weekid
     * @return
     */
    @Select("select userid,projectid,weekid,sum(hour) as hour from Work where userid=#{userid} AND weekid=#{weekid} GROUP BY projectid")
    List<Work> selectOneWork(@Param("userid") Integer userid,
                             @Param("weekid") String weekid);
}
//