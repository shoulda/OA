package com.siemens.oa.service;

import com.siemens.oa.entity.Work;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface WorkService {
    /**
     * 插入work条目接口
     *
     * @param work
     */
    void insertWork(Work work);

    /**
     * 根据userID和起止时间来查询work条目
     *
     * @param USERID
     * @param start
     * @param end
     * @return
     */
    List<Work> selectWorkByScope(Integer USERID, String start, String end);

    /**
     * 查询所有work记录
     *
     * @return
     */
    List<Work> selectWork();

    void updateWork(Work work);

    Work selectWorkFrom(Work work);

//    List<Work> selectWorkByID(Integer USERID, String start, String end);

//    JSONObject getFromDB(String userid, String start, String end);
}
