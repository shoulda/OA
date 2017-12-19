package com.siemens.oa.service;

import com.siemens.oa.dao.UserDao;
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
     * xujin : WorkToJson2
     *
     * @param works
     * @param weekid
     * @return
     */
    @Override
    public JsonListToWork2 WorkToJson2(List<Work> works, String weekid) {
        JsonListToWork2 listToWork = new JsonListToWork2();
        List<JsonListToWork2.WorkEntity> workEntityList = new ArrayList<>();
        listToWork.setWeekId(weekid);
        for (Work work : works) {
            if (!ProjectIfin(work, workEntityList)) {
                JsonListToWork2.WorkEntity workEntity = listToWork.new WorkEntity();
                workEntity.setProjectId(work.getProjectid());
                workEntity.setProjectName(projectDao.selectProjectById(work.getProjectid()).getProjectname());

                List<JsonListToWork2.WorkEntity.TasksEntity> tasksEntityList = new ArrayList<>();
                JsonListToWork2.WorkEntity.TasksEntity tasksEntity = workEntity.new TasksEntity();
                tasksEntity.setTaskId(work.getTaskid());
                tasksEntity.setTaskName(taskDao.selectTaskById(work.getTaskid()).getTaskname());

                List<JsonListToWork2.WorkEntity.TasksEntity.DaysEntity> daysEntityList = new ArrayList<>();
                JsonListToWork2.WorkEntity.TasksEntity.DaysEntity daysEntity = tasksEntity.new DaysEntity();

                daysEntity.setHour(work.getHour());
                daysEntity.setStamp(work.getStamp());
                daysEntity.setM_STATUS(work.getM_STATUS());
                daysEntityList.add(daysEntity);

                tasksEntity.setDays(daysEntityList);
                tasksEntityList.add(tasksEntity);

                workEntity.setTasks(tasksEntityList);
                workEntityList.add(workEntity);
            }
        }
        listToWork.setWork(workEntityList);
        return listToWork;
    }


    public boolean ProjectIfin(Work work, List<JsonListToWork2.WorkEntity> workEntityList) {
        boolean flag = false;
        if (workEntityList.size() > 0) {
            for (JsonListToWork2.WorkEntity workEntity : workEntityList) {
                if (work.getProjectid() == workEntity.getProjectId()) {
                    if (!TaskIfin(work, workEntity.getTasks())) {

                        JsonListToWork2.WorkEntity.TasksEntity tasksEntity = workEntity.new TasksEntity();
                        tasksEntity.setTaskId(work.getTaskid());
                        tasksEntity.setTaskName(taskDao.selectTaskById(work.getTaskid()).getTaskname());

                        List<JsonListToWork2.WorkEntity.TasksEntity.DaysEntity> daysEntityList = new ArrayList<>();
                        JsonListToWork2.WorkEntity.TasksEntity.DaysEntity daysEntity = tasksEntity.new DaysEntity();
                        daysEntity.setHour(work.getHour());
                        daysEntity.setStamp(work.getStamp());
                        daysEntity.setM_STATUS(work.getM_STATUS());
                        daysEntityList.add(daysEntity);

                        tasksEntity.setDays(daysEntityList);
                        workEntity.getTasks().add(tasksEntity);
                    }
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean TaskIfin(Work work, List<JsonListToWork2.WorkEntity.TasksEntity> tasksEntityList) {
        boolean flag = false;
        if (tasksEntityList.size() > 0) {
            for (JsonListToWork2.WorkEntity.TasksEntity tasksEntity : tasksEntityList) {
                if (work.getTaskid() == tasksEntity.getTaskId()) {
                    JsonListToWork2.WorkEntity.TasksEntity.DaysEntity daysEntity = tasksEntity.new DaysEntity();
                    daysEntity.setHour(work.getHour());
                    daysEntity.setStamp(work.getStamp());
                    daysEntity.setM_STATUS(work.getM_STATUS());
                    tasksEntity.getDays().add(daysEntity);
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
     * 饼状图使用
     *
     * @param userid
     * @param weekid
     * @return
     */
    @Override
    public Series WorkToSeries(Integer userid, String weekid, Integer weekConut) {
        List<Work> works = workDao.selectWorkByUW(userid, weekid);
        Series series = new Series();
        series.setType("pie");
        series.setName(userDao.selectUserById(userid).getUsername() + "第" + weekConut + "周的工作记录");
        ArrayList<Series.DataEntity> dataEntityArrayList = new ArrayList<>();
        for (Work work : works) {
            Series.DataEntity dataEntity = series.new DataEntity();
            dataEntity.setName(projectDao.selectProjectById(work.getProjectid()).getProjectname().toUpperCase() + " [" + taskDao.selectTaskById(work.getTaskid()).getTaskname() + "]");
            dataEntity.setY(work.getHour());
            dataEntityArrayList.add(dataEntity);
        }
        series.setData(dataEntityArrayList);
        System.out.print(series);
        return series;
    }

    /**
     * 根据projectID和weekID查询某工程一周参与人员的耗时数据
     * 饼状图使用
     *
     * @param projectid
     * @param weekid
     * @param weekConut
     * @return
     */
    @Override
    public Series ProjectToSeries(Integer projectid, String weekid, Integer weekConut) {
        List<Work> works = workDao.selectWorkByPW(projectid, weekid);
        Series series = new Series();
        series.setType("pie");
        series.setName(projectDao.selectProjectById(projectid).getProjectname() + "第" + weekConut + "周的工作记录");
        ArrayList<Series.DataEntity> dataEntities = new ArrayList<>();
        for (Work work : works) {
            Series.DataEntity dataEntity = series.new DataEntity();
            dataEntity.setName(userDao.selectUserById(work.getUserid()).getUsername().toUpperCase() + " [" + taskDao.selectTaskById(work.getTaskid()).getTaskname() + "]");
            dataEntity.setY(work.getHour());
            dataEntities.add(dataEntity);
        }
        series.setData(dataEntities);
        System.out.print(series);
        return series;
    }

    /**
     * 查询某个人一周所参与的工程和时间
     * 用于报表数据
     *
     * @param userid
     * @param weekid
     * @return
     */
    @Override
    public List<Work> selectOneWork(Integer userid, String weekid) {
        return workDao.selectOneWork(userid, weekid);
    }

    @Override
    public List<String> selectAllWeekID() {
        return workDao.selectAllWeekID();
    }
}
//