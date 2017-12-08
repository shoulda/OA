package com.siemens.oa.service;

import com.google.gson.Gson;
import com.siemens.oa.dao.WorkDao;
import com.siemens.oa.entity.JsonListToWork;
import com.siemens.oa.entity.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final WorkDao workDao;

    @Autowired
    public WorkServiceImpl(WorkDao workDao) {
        this.workDao = workDao;
    }

    /**
     * 插入一条新工作记录
     *
     * @param work
     */
    @Override
    public void insertWork(Work work) {
        workDao.insertWork(work);
    }

    /**
     * 删除工作记录
     *
     * @param work
     */
    @Override
    public void deleteWork(Work work) {
        workDao.deleteWork(work);
    }

    /**
     * 根据工作ID更新工作信息
     *
     * @param work
     */
    @Override
    public void updateWork(Work work) {
        workDao.updateWork(work);
    }

    /**
     * 查找所有工作记录
     *
     * @return
     */
    @Override
    public List<Work> selectWork() {
        return workDao.selectWork();
    }

    /**
     * 根据UTPS查找用户工作信息
     *
     * @param work
     * @return
     */
    @Override
    public Work selectWorkByUTPS(Work work) {
        return workDao.selectWorkByUTPS(work);
    }

    /**
     * 根据用户ID和查找指定范围工作记录
     *
     * @param userid
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<Work> selectWorkByScope(Integer userid, String start, String end) {
        return workDao.selectWorkByScope(userid, start, end);
    }

    /**
     * Json转化为workList
     *
     * @param JsonStr
     * @return
     */
    @Override
    public List<Work> JsonToWork(String JsonStr) {
        JsonListToWork jsonwork = new Gson().fromJson(JsonStr, JsonListToWork.class);
        System.out.println(jsonwork.getWork().size());
        ArrayList<Work> works = new ArrayList<Work>();
        List<JsonListToWork.WorkEntity> work = jsonwork.getWork();
        for (JsonListToWork.WorkEntity workEntity : work) {
            String weekid = String.valueOf(jsonwork.getWeekId());
            Integer projectid = workEntity.getProjectId();
            for (JsonListToWork.WorkEntity.TasksEntity entity : workEntity.getTasks()) {
                Work work1 = new Work();
                work1.setWeekid(weekid);
                work1.setProjectid(projectid);
                work1.setTaskid(entity.getTaskId());
                work1.setHour(entity.getHour());
                work1.setStamp(String.valueOf(entity.getStamp()));
                works.add(work1);
            }
        }
        return works;
    }
}