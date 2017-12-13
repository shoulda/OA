package com.siemens.oa.service;

import com.siemens.oa.entity.JsonListToWork;
import com.siemens.oa.entity.JsonListToWork2;
import com.siemens.oa.entity.Series;
import com.siemens.oa.entity.Work;
import org.apache.ibatis.annotations.Param;


import java.awt.*;
import java.util.List;
import java.util.Map;

public interface WorkService {

    void insertWork(Work work);

    void deleteWork(Work work);

    void updateWork(Work work);

    List<Work> selectWork();

    Work selectWorkByUTPS(Work work);

    List<Work> selectWorkByWeekId(Integer userid, String weekid);

    List<Work> JsonToWork(String JsonStr);

    JsonListToWork2 WorkToJson(List<Work> works, String weekid);

    Series WorkToSeries(Integer userid, String weekid, Integer weekConut);

    Map<String, Object> SubStatus(boolean success, Integer code, String message);
}
