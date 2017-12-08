package com.siemens.oa.service;

import com.google.gson.Gson;
import com.siemens.oa.dao.ProjectDao;
import com.siemens.oa.dao.TaskDao;
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
    private final ProjectDao projectDao ;
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

    @Override
    public JsonListToWork WorkToJson(List<Work> works,String weekid) {
        JsonListToWork json = new JsonListToWork();
        List<JsonListToWork.WorkEntity> workList = new ArrayList<JsonListToWork.WorkEntity>() ;
        json.setWeekId(weekid);


        for (Work temp_work : works) {
            System.out.println(temp_work);
            if (existPro(workList,temp_work.getProjectid()) == 0){
                System.out.println(temp_work);
                JsonListToWork.WorkEntity work = json.new WorkEntity();
                List<JsonListToWork.WorkEntity.TasksEntity> taskList = new ArrayList<JsonListToWork.WorkEntity.TasksEntity>();
                JsonListToWork.WorkEntity.TasksEntity task = work.new TasksEntity();

                work.setProjectId(temp_work.getProjectid());
                System.out.println(work.getProjectId());
                work.setProjectName(projectDao.selectProjectById(temp_work.getProjectid()).getProjectname());
                //System.out.println(work.getProjectName());
                task.setHour(temp_work.getHour());
                task.setStamp(temp_work.getStamp());
                task.setTaskId(temp_work.getTaskid());
                System.out.println(taskDao.selectTaskById(temp_work.getTaskid()).getTaskname());
                task.setTaskName(taskDao.selectTaskById(temp_work.getTaskid()).getTaskname());
                System.out.println(task.getTaskId());
                task.setHour(temp_work.getHour());

                taskList.add(task);
                work.setTasks(taskList);

                workList.add(work);
            }
            else {
                JsonListToWork.WorkEntity work = workList.get(existPro(workList,temp_work.getProjectid()));
                JsonListToWork.WorkEntity.TasksEntity task = work.new TasksEntity();

                task.setHour(temp_work.getHour());
                task.setStamp(temp_work.getStamp());
                task.setTaskId(temp_work.getTaskid());
                task.setTaskName(taskDao.selectTaskById(task.getTaskId()).getTaskname());
                task.setHour(temp_work.getHour());
                work.getTasks().add(task);

            }

//            for (JsonListToWork.WorkEntity jsonWork : workList) {
//
//
//            }
//                if (jsonWork.getProjectId() != temp_work.getProjectid()) {
//                    JsonListToWork.WorkEntity work = json.new WorkEntity();
//                    List<JsonListToWork.WorkEntity.TasksEntity> taskList = jsonWork.getTasks();
//                    JsonListToWork.WorkEntity.TasksEntity task = work.new TasksEntity();
//                    work.setProjectId(temp_work.getProjectid());
//                    work.setProjectName(projectDao.selectProjectById(work.getProjectId()).getProjectname());
//                    task.setHour(temp_work.getHour());
//                    task.setStamp(Integer.valueOf(temp_work.getStamp()));
//                    task.setTaskId(temp_work.getTaskid());
//                    task.setTaskName(taskDao.selectTaskById(task.getTaskId()).getTaskname());
//                    task.setHour(temp_work.getHour());
//                    taskList.add(task);
//                    work.setTasks(taskList);
//                    workList.add(work);
//                }
//                else {
//                    JsonListToWork.WorkEntity work = json.new WorkEntity();
//                    JsonListToWork.WorkEntity.TasksEntity task = work.new TasksEntity();
//
//                    work.setProjectId(temp_work.getProjectid());
//                    work.setProjectName(projectDao.selectProjectById(work.getProjectId()).getProjectname());
//                    task.setHour(temp_work.getHour());
//                    task.setStamp(Integer.valueOf(temp_work.getStamp()));
//                    task.setTaskId(temp_work.getTaskid());
//                    task.setTaskName(taskDao.selectTaskById(task.getTaskId()).getTaskname());
//                    task.setHour(temp_work.getHour());
//
//                    work.getTasks().add(task);
//                    workList.add(work);
//
//                }
//            }
        }
        System.out.println(workList);
        json.setWork(workList);

        return json;
    }


    private int existPro(List<JsonListToWork.WorkEntity> workList,int projectid){
        JsonListToWork.WorkEntity workEntity  ;
        if (workList == null) return 0;
        else {
            for (int i = 0;i < workList.size();i++){
                workEntity = workList.get(i);
                if (workEntity.getProjectId() == projectid) return i;
            }
            return 0;
        }

    }

}