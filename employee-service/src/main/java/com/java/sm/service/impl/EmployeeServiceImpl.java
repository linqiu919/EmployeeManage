package com.java.sm.service.impl;

import com.github.pagehelper.PageInfo;
import com.java.sm.entity.Employee;
import com.java.sm.entity.EmployeeExample;
import com.java.sm.mapper.EmployeeMapper;
import com.java.sm.page.PageBean;
import com.java.sm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName EmployeeServiceImpl.java
 * @DescriPtion TODO
 * @CreateTime 2021年06月14日 22:35:00
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee doLogin(String phone) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmployeePhoneEqualTo(phone);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if(employees.isEmpty()){
            return null;
        }else{
            return employees.get(0);
        }
    }

    @Override
    public PageBean<Employee> findAll() {
        List<Employee> employees = employeeMapper.selectByExample(null);
        //获取每一页的信息
        PageInfo<Employee> pageInfo = new PageInfo<>(employees);
        return PageBean.init(pageInfo.getTotal(),employees);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return  employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public void deleteByIds(List<Integer> delIds) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmployeeIdIn(delIds);
        employeeMapper.deleteByExample(employeeExample);
    }

    @Override
    public List<Employee> findList() {
        List<Employee> employees = employeeMapper.selectByExample(null);
        return employees;
    }
}
