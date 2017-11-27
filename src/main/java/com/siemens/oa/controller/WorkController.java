package com.siemens.oa.controller;


import com.siemens.oa.entity.Work;
import com.siemens.oa.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2017/11/22
 * \* Time: 15:01
 * \
 */
@RestController
@RequestMapping("/work")
public class WorkController {
    @Autowired
    private WorkService workService;

    /**
     * 根据weekid和userid查询work记录
     *
     * @param workid
     */
    @GetMapping("/selectWorkByUW")
    public List<Work> selectWorkByUW(Integer workid) {
        return workService.selectWorkByUW(workid);
    }

    /**
     * 新建work
     *
     * @param work
     */
    @PostMapping("/insertWork")
    public void insertWork(Work work) {
        workService.insertWork(work);
    }
}