package com.example.org.dao;

import com.example.org.bean.Employees;

import java.util.List;

public interface EmployeesImpl {
    boolean addEmployee(Employees employees);
    List<Employees> getEmployees();
    Employees getEmployeeByID(int id);
}
