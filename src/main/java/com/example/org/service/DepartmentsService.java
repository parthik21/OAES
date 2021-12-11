package com.example.org.service;

import com.example.org.bean.Departments;
import com.example.org.dao.DepartmentsDAO;

import java.util.List;

public class DepartmentsService {
    DepartmentsDAO dao = new DepartmentsDAO();
    public boolean addDepartment(Departments dept)
    {
        return dao.addDepartment(dept);
    }
    public List<Departments> getDepartments(){
        return dao.getDepartments();
    }
    public Departments getDepartmentById(int id)
    {
        return dao.getDepartmentById(id);
    }
    public boolean addDepartment(String name){
        return dao.addDepartment(name);
    }
}
