package com.example.org.service;

import com.example.org.bean.Employees;
import com.example.org.dao.EmployeesDAO;

import java.util.List;

public class EmployeesService {
    EmployeesDAO dao = new EmployeesDAO();
    public boolean addEmployee(Employees emp)
    {
        return dao.addEmployee(emp);
    }
    public List<Employees> getEmployees()
    {
        return dao.getEmployees();
    }
    public Employees getEmployeesById(int id)
    {
        return dao.getEmployeeByID(id);
    }
}
