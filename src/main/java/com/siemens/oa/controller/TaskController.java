package com.siemens.oa.controller;

import com.siemens.oa.dao.TaskDao;
import com.siemens.oa.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 14:52
 * \
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskDao taskMapper;

    /**
     * 根据taskid查找task
     * @param TASKID
     */
    @GetMapping("/selectTaskByTaskID")
    public void selectTaskByTaskID(Integer TASKID) {
        taskMapper.selectTaskByTaskID(TASKID);
    }

    /**
     * 插入新task
     * @param task
     */
    @PostMapping("/insertTask")
    public void insertTask(Task task) {
        taskMapper.insertTask(task);
    }

}