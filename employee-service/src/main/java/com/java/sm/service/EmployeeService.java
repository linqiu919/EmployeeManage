package com.java.sm.service;

import com.java.sm.entity.Employee;
import com.java.sm.page.PageBean;

import java.util.List;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName EmployeeService.java
 * @CreateTime 2021年06月14日 22:35:00
 */
public interface EmployeeService {

    Employee doLogin(String phone);

    PageBean<Employee> findAll();

    void addEmployee(Employee employee);

    Employee findById(Integer id);

    void updateEmployee(Employee employee);

    void deleteByIds(List<Integer> delIds);

    List<Employee> findList();
}
