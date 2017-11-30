package com.siemens.oa.service;

import com.siemens.oa.dao.TaskDao;
import com.siemens.oa.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/30
 * \* Time: 14:34
 * \
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    /**
     * 插入一条新任务记录
     *
     * @param task
     */
    @Override
    public void insertTask(Task task) {
        taskDao.insertTask(task);
    }

    /**
     * 根据任务名删除任务
     *
     * @param taskname
     */
    @Override
    public void deleteTask(String taskname) {
        taskDao.deleteTask(taskname);
    }

    /**
     * 根据任务ID更新任务
     *
     * @param task
     */
    @Override
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    /**
     * 根据任务名查询任务
     *
     * @param taskname
     * @return
     */
    @Override
    public Task selectTaskByTaskName(String taskname) {
        return taskDao.selectTaskByTaskName(taskname);
    }
}