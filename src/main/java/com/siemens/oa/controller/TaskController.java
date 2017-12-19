package com.siemens.oa.controller;

import com.siemens.oa.annotation.AuthDetec;
import com.siemens.oa.entity.Task;
import com.siemens.oa.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/12/10
 * \* Time: 11:10
 * \
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 从task表获取所有task记录
     *
     * @return
     */
    @GetMapping("/getAllTask")
    public List<Task> selectAllTask() {
        return taskService.selectAllTask();
    }

    @PostMapping("/insertTask")
    @AuthDetec(authorities = "admin")
    public void insertTask(Task task){
        taskService.insertTask(task);
    }

}
//