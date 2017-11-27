package com.siemens.oa.service;

import com.siemens.oa.dao.WorkDao;
import com.siemens.oa.entity.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/23
 * \* Time: 10:04
 * \
 */
@Service
public class WorkServiceImpl implements WorkService {

    private final WorkDao workMapper;

    @Autowired
    public WorkServiceImpl(WorkDao workMapper) {
        this.workMapper = workMapper;
    }

    @Override
    public List<Work> selectWorkByUW(Integer workid) {
        return workMapper.selectWorkByUW(workid);
    }

    @Override
    public void insertWork(Work work) {
        workMapper.insertWork(work);
    }

    @Override
    public List<Work> selectWorkByScope(Integer userid, String start, String end) {
        return workMapper.selectWorkByScope(userid, start, end);
    }
}