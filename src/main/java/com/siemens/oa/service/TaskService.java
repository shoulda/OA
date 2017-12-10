package com.siemens.oa.service;

import com.siemens.oa.entity.Task;

import java.util.List;

public interface TaskService {

    void insertTask(Task task);

    void deleteTask(String TASKNAME);

    void updateTask(Task task);

    Task selectTaskByTaskName(String TASKNAME);

    List<Task> selectAllTask();

}
