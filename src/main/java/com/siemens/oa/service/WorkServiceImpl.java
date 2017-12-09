package com.siemens.oa.service;

import com.siemens.oa.entity.JsonListToWork;
import com.google.gson.Gson;
import com.siemens.oa.dao.ProjectDao;
import com.siemens.oa.dao.TaskDao;
import com.siemens.oa.dao.WorkDao;
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
        System.out.println(jsonwork.getWork().size());
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
            if (existPro(workList, temp_work.getProjectid()) == 0) {
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
//        System.out.println(workList.size());
        json.setWork(workList);
//        System.out.println(json.getWork().get(0).getTasks().get(0).getTaskName());
        return json;
    }

    private int existPro(List<JsonListToWork.WorkEntity> workList, int projectid) {
        JsonListToWork.WorkEntity workEntity;
        if (workList == null) return 0;
        else {
            for (int i = 0; i < workList.size(); i++) {
                workEntity = workList.get(i);
                if (workEntity.getProjectId() == projectid) return i;
            }
            return 0;
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
        System.out.println(workEntityList);
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

}