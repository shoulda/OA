package com.siemens.oa.service;

import com.siemens.oa.dao.UserDao;
import com.siemens.oa.entity.JsonListToWork;
import com.google.gson.Gson;
import com.siemens.oa.dao.ProjectDao;
import com.siemens.oa.dao.TaskDao;
import com.siemens.oa.dao.WorkDao;
import com.siemens.oa.entity.Series;
import com.siemens.oa.entity.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final ProjectDao projectDao;
    private final TaskDao taskDao;
    private final UserDao userDao;

    @Autowired
    public WorkServiceImpl(WorkDao workDao, ProjectDao projectDao, TaskDao taskDao, UserDao userDao) {
        this.workDao = workDao;
        this.projectDao = projectDao;
        this.taskDao = taskDao;
        this.userDao = userDao;
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
     * @param
     * @param
     * @return
     */
    @Override
    public List<Work> selectWorkByWeekId(Integer userid, String weekid) {
        return workDao.selectWorkByWeekId(userid, weekid);
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
        System.out.println("总共获得" + jsonwork.getWork().size() + "条work元素");
        ArrayList<Work> works = new ArrayList<>();
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

    /**
     * liyuhui : WorkToJson
     *
     * @param works
     * @param weekid
     * @return
     */
    @Override
    public JsonListToWork WorkToJson(List<Work> works, String weekid) {
        JsonListToWork json = new JsonListToWork();
        List<JsonListToWork.WorkEntity> workList = new ArrayList<JsonListToWork.WorkEntity>();
        json.setWeekId(weekid);
        for (Work temp_work : works) {
            if (existPro(workList, temp_work.getProjectid()) == -1) {
                JsonListToWork.WorkEntity work = json.new WorkEntity();
                List<JsonListToWork.WorkEntity.TasksEntity> taskList = new ArrayList<JsonListToWork.WorkEntity.TasksEntity>();
                JsonListToWork.WorkEntity.TasksEntity task = work.new TasksEntity();
                work.setProjectId(temp_work.getProjectid());
                work.setProjectName(projectDao.selectProjectById(temp_work.getProjectid()).getProjectname());
                task.setHour(temp_work.getHour());
                task.setStamp(temp_work.getStamp());
                task.setTaskId(temp_work.getTaskid());
                task.setTaskName(taskDao.selectTaskById(temp_work.getTaskid()).getTaskname());
                task.setHour(temp_work.getHour());
                taskList.add(task);
                work.setTasks(taskList);
                workList.add(work);
            } else {
                JsonListToWork.WorkEntity work = workList.get(existPro(workList, temp_work.getProjectid()));
                JsonListToWork.WorkEntity.TasksEntity task = work.new TasksEntity();
                task.setHour(temp_work.getHour());
                task.setStamp(temp_work.getStamp());
                task.setTaskId(temp_work.getTaskid());
                task.setTaskName(taskDao.selectTaskById(task.getTaskId()).getTaskname());
                task.setHour(temp_work.getHour());
                work.getTasks().add(task);
            }
        }
        json.setWork(workList);
        return json;
    }


    private int existPro(List<JsonListToWork.WorkEntity> workList, int projectid) {
        JsonListToWork.WorkEntity workEntity;
        if (workList.size() == 0) return -1;
        else {
            for (int i = 0; i < workList.size(); i++) {
                workEntity = workList.get(i);
                if (workEntity.getProjectId() == projectid) return i;
            }
            return -1;
        }
    }


    /**
     * xujin : WorkToJson2
     *
     * @param works
     * @param weekid
     * @return
     */
    @Override
    public JsonListToWork WorkToJson2(List<Work> works, String weekid) {
        JsonListToWork listToWork = new JsonListToWork();
        List<JsonListToWork.WorkEntity> workEntityList = new ArrayList<>();
        listToWork.setWeekId(weekid);
        for (Work work : works) {
            if (!ProjectIfin(work, workEntityList)) {
                JsonListToWork.WorkEntity workEntity = listToWork.new WorkEntity();
                workEntity.setProjectId(work.getProjectid());
                workEntity.setProjectId(work.getProjectid());
                workEntity.setProjectName(projectDao.selectProjectById(work.getProjectid()).getProjectname());
                List<JsonListToWork.WorkEntity.TasksEntity> tasksEntityList = new ArrayList<>();
                JsonListToWork.WorkEntity.TasksEntity tasksEntity = workEntity.new TasksEntity();
                tasksEntity.setHour(work.getHour());
                tasksEntity.setStamp(work.getStamp());
                tasksEntity.setTaskId(work.getTaskid());
                tasksEntity.setTaskName(taskDao.selectTaskById(work.getTaskid()).getTaskname());
                tasksEntityList.add(tasksEntity);
                workEntity.setTasks(tasksEntityList);
                workEntityList.add(workEntity);
            }
        }
        listToWork.setWork(workEntityList);
        return listToWork;
    }


    public boolean ProjectIfin(Work work, List<JsonListToWork.WorkEntity> workEntityList) {
        boolean flag = false;
        if (workEntityList.size() > 0) {
            for (JsonListToWork.WorkEntity workEntity : workEntityList) {
                if (work.getProjectid() == workEntity.getProjectId()) {
                    JsonListToWork.WorkEntity.TasksEntity tasksEntity = workEntity.new TasksEntity();
                    tasksEntity.setHour(work.getHour());
                    tasksEntity.setStamp(work.getStamp());
                    tasksEntity.setTaskId(work.getTaskid());
                    tasksEntity.setTaskName(taskDao.selectTaskById(work.getTaskid()).getTaskname());
                    workEntity.getTasks().add(tasksEntity);
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 提交状态函数
     *
     * @param success
     * @param code
     * @param message
     * @return
     */
    @Override
    public Map<String, Object> SubStatus(boolean success, Integer code, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("message", message);
        map.put("code", code);
        return map;
    }

    /**
     * 根据userID和weekID获取某个人某一周的工作记录
     *
     * @param userid
     * @param weekid
     * @return
     */
    @Override
    public Series WorkToSeries(Integer userid, String weekid, Integer weekConut) {
        List<Work> works = workDao.selectWorkByWeekId(userid, weekid);
        Series series = new Series();
        series.setType("pie");
        series.setName(userDao.selectUserById(userid).getUsername() + "第" + weekConut + "周的工作记录");
        ArrayList<Series.DataEntity> dataEntities = new ArrayList<>();
        for (Work work : works) {
            Series.DataEntity dataEntity = series.new DataEntity();
            dataEntity.setName(projectDao.selectProjectById(work.getProjectid()).getProjectname() + "-" + taskDao.selectTaskById(work.getTaskid()).getTaskname());
            dataEntity.setY(work.getHour());
            dataEntities.add(dataEntity);
        }
        series.setData(dataEntities);
        System.out.print(series);
        return series;
    }
}