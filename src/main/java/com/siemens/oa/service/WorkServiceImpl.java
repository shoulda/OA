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


    /**
     * 插入一条工作条目
     *
     * @param work
     */
    @Override
    public void insertWork(Work work) {
        workMapper.insertWork(work);
    }


    /**
     * 根据userid和旗帜时间查询work条目
     *
     * @param USERID
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<Work> selectWorkByScope(Integer USERID, String start, String end) {
        return workMapper.selectWorkByScope(USERID, start, end);
    }

    @Override
    public List<Work> selectWork() {
        return workMapper.selectWork();
    }

    @Override
    public void updateWork(Work work) {
        workMapper.updateWork(work);
    }

    @Override
    public Work selectWorkFrom(Work work) {
        return workMapper.selectWorkFrom(work);
    }

//    @Override
//    public List<Work> selectWorkByID(Integer USERID, String start, String end) {
//        return workMapper.selectWorkByID(USERID, start, end);
//    }

//    @Override
//    public JSONObject getFromDB(String userid, String start, String end) {
//        JSONObject object = new JSONObject();
//        List<Work> workList = workMapper.selectWorkByScope(Integer.getInteger(userid), start, end);
//        for (int i = 0; i < workList.size(); ++i) {
//            object.put(i, workTojson(workList.get(i)));
//        }
//        return object;
//    }

//    /**
//     * work对象映射为json对象
//     *
//     * @param work
//     * @return
//     */
//    public JSONObject workTojson(Work work) {
//        JSONObject object = new JSONObject();
//        object.put("USERID", work.getUSERID().toString());
//        object.put("PROJECTID", work.getPROJECTID().toString());
//        object.put("TASKID", work.getTASKID().toString());
//        object.put("STAMP", work.getSTAMP());
//        object.put("HOUR", work.getHOUR().toString());
//        object.put("M_STATUS", work.getM_STATUS().toString());
//        return object;
//    }
}