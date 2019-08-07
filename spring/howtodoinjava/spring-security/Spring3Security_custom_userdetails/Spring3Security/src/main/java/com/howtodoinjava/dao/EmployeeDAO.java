package com.howtodoinjava.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.howtodoinjava.entity.EmployeeEntity;

public interface EmployeeDAO 
{
    public void addEmployee(EmployeeEntity employee);
    public List<EmployeeEntity> getAllEmployees();
    public void deleteEmployee(Integer employeeId);
    public UserDetails loadUserByUsername(String username);
}