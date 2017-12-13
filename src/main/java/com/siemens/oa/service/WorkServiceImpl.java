package com.siemens.oa.service;

import com.siemens.oa.entity.JsonListToWork;
import com.google.gson.Gson;
import com.siemens.oa.dao.ProjectDao;
import com.siemens.oa.dao.TaskDao;
import com.siemens.oa.dao.WorkDao;
import com.siemens.oa.entity.JsonListToWork2;
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

    @Autowired
    public WorkServiceImpl(WorkDao workDao, ProjectDao projectDao, TaskDao taskDao) {
        this.workDao = workDao;
        this.projectDao = projectDao;
        this.taskDao = taskDao;
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
    public JsonListToWork2 WorkToJson(List<Work> works, String weekid) {
        JsonListToWork2 json = new JsonListToWork2();
        List<JsonListToWork2.WorkEntity> workList = new ArrayList<>();
        json.setWeekId(weekid);
        System.out.println(weekid);
        for (Work temp_work : works) {
            if (existPro(workList, temp_work.getProjectid()) == -1) {
                JsonListToWork2.WorkEntity work = json.new WorkEntity();
                List<JsonListToWork2.WorkEntity.TasksEntity> taskList = new ArrayList<>();
                JsonListToWork2.WorkEntity.TasksEntity task = work.new TasksEntity();
                List<JsonListToWork2.WorkEntity.TasksEntity.DaysEntity> dayList = new ArrayList<>();
                JsonListToWork2.WorkEntity.TasksEntity.DaysEntity day = task.new DaysEntity();
                work.setProjectId(temp_work.getProjectid());
                work.setProjectName(projectDao.selectProjectById(temp_work.getProjectid()).getProjectname());
                task.setTaskId(temp_work.getTaskid());
                task.setTaskName(taskDao.selectTaskById(temp_work.getTaskid()).getTaskname());
                day.setHour(temp_work.getHour());
                day.setStamp(temp_work.getStamp());
                day.setM_STATUS(temp_work.getM_STATUS());
                dayList.add(day);
                task.setDays(dayList);
                taskList.add(task);
                work.setTasks(taskList);
                workList.add(work);
            } else {
                JsonListToWork2.WorkEntity work = workList.get(existPro(workList, temp_work.getProjectid()));
                if (existTask(work.getTasks(), temp_work.getTaskid()) == -1) {
                    JsonListToWork2.WorkEntity.TasksEntity task = work.new TasksEntity();
                    List<JsonListToWork2.WorkEntity.TasksEntity.DaysEntity> dayList = new ArrayList<>();
                    JsonListToWork2.WorkEntity.TasksEntity.DaysEntity day = task.new DaysEntity();
                    task.setTaskId(temp_work.getTaskid());
                    task.setTaskName(taskDao.selectTaskById(temp_work.getTaskid()).getTaskname());
                    day.setHour(temp_work.getHour());
                    day.setStamp(temp_work.getStamp());
                    day.setM_STATUS(temp_work.getM_STATUS());
                    dayList.add(day);
                    task.setDays(dayList);
                } else {
                    JsonListToWork2.WorkEntity.TasksEntity tasksEntity = work.getTasks().get(existTask(work.getTasks(), temp_work.getTaskid()));
                    JsonListToWork2.WorkEntity.TasksEntity.DaysEntity day = tasksEntity.new DaysEntity();
                    day.setHour(temp_work.getHour());
                    day.setStamp(temp_work.getStamp());
                    day.setM_STATUS(temp_work.getM_STATUS());
                    System.out.println(day.getStamp());
                    tasksEntity.getDays().add(day);
                }
            }
        }
        json.setWork(workList);
        return json;
    }

    @Override
    public Series WorkToSeries(Integer userid, String weekid, Integer weekConut) {
        return null;
    }

    private int existPro(List<JsonListToWork2.WorkEntity> workList, int projectid) {
        JsonListToWork2.WorkEntity workEntity;
        if (workList.size() == 0) return -1;
        else {
            for (int i = 0; i < workList.size(); i++) {
                workEntity = workList.get(i);
                if (workEntity.getProjectId() == projectid) return i;

            }
            return -1;
        }
    }

    private int existTask(List<JsonListToWork2.WorkEntity.TasksEntity> taskList, int taskid) {
        JsonListToWork2.WorkEntity.TasksEntity tasksEntity;
        if (taskList.size() == 0) return -1;
        else {
            for (int i = 0; i < taskList.size(); i++) {
                tasksEntity = taskList.get(i);
                if (tasksEntity.getTaskId() == taskid) return i;
            }
        }
        return -1;
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
}