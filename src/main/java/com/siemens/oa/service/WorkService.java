package com.siemens.oa.service;

import com.siemens.oa.entity.Work;


import java.util.List;

public interface WorkService {

//    List<Work> selectWorkByUW(Integer weekid);

    void insertWork(Work work);

    List<Work> selectWorkByScope(Integer USERID, String start, String end);

//    List<Work> selectWorkByID(Integer USERID, String start, String end);

//    JSONObject getFromDB(String userid, String start, String end);
}
