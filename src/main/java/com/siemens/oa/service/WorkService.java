package com.siemens.oa.service;

import com.siemens.oa.entity.Work;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface WorkService {

    void insertWork(Work work);

    void deleteWork(Work work);

    void updateWork(Work work);

    List<Work> selectWork();

    Work selectWorkByUTPS(Work work);

    List<Work> selectWorkByScope(Integer userid, String start, String end);



}
