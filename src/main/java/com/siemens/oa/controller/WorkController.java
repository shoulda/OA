package com.siemens.oa.controller;


import com.siemens.oa.entity.Work;
import com.siemens.oa.service.WorkService;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * 新建work
     *
     * @param
     */

    @PostMapping("/insertWork")
    public void insertWork(@RequestBody Work work) {
        workService.insertWork(work);
    }

    @GetMapping("/selectWorkByScope")
    public List<Work> selectWorkByScope(Integer USERID, String start, String end) {
        List<Work> works = workService.selectWorkByScope(USERID, start, end);
        System.out.println(works);
        return works;
    }

}
