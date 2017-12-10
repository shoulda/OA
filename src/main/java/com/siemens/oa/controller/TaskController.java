package com.siemens.oa.controller;

import com.siemens.oa.entity.Task;
import com.siemens.oa.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    TaskService taskService;

    @GetMapping("/selectAllTask")
    public List<Task> selectAllTask() {
        return taskService.selectAllTask();
    }

}