package com.java.sm.controller;

import com.github.pagehelper.PageHelper;
import com.java.sm.entity.Employee;
import com.java.sm.page.PageBean;
import com.java.sm.result.AxiosResult;
import com.java.sm.service.EmployeeService;
import com.java.sm.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName EmployeeController.java
 * @CreateTime 2021年06月15日 12:23:00
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     */
    @GetMapping("findAll")
    public AxiosResult<PageBean<Employee>> findAll(int currentPage,int pageSize){
        PageHelper.startPage(currentPage,pageSize);
        PageBean<Employee> pageBean = employeeService.findAll();
        return AxiosResult.success(pageBean);
    }

    /**
     * 添加员工
     * @param employee
     */
    @RequestMapping("add")
    public AxiosResult<Void> addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return AxiosResult.success();
    }

    /**
     * 通过ID查询员工
     * @param id
     */
    @RequestMapping("findById/{id}")
    public AxiosResult<Employee> findById(@PathVariable Integer id){
        Employee employee = employeeService.findById(id);
        return AxiosResult.success(employee);
    }

    /**
     * 更新员工信息
     * @param employee
     */
    @RequestMapping("updateEmployee")
    public AxiosResult<Void> updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return AxiosResult.success();
    }

    /**
     * 删除员工
     * @param delIds
     */
    @DeleteMapping("delEmployee/{delIds}")
    public AxiosResult<Void> delEmployee(@PathVariable List<Integer> delIds){
            employeeService.deleteByIds(delIds);
        return AxiosResult.success();
    }




}
