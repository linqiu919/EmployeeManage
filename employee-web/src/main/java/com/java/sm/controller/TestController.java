package com.java.sm.controller;

import com.java.sm.entity.Employee;
import com.java.sm.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName TestController.java
 * @DescriPtion TODO
 * @CreateTime 2021年06月14日 20:01:00
 */
@RestController
public class TestController {
    @GetMapping("testMode")
    public String test(){
        return "success";
    }

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("testMode2")
    public List<Employee> testMode2(){
        return employeeMapper.selectByExample(null);
    }

}
