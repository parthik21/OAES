package com.example.org.dao;

import com.example.org.bean.Departments;

import java.util.List;

public interface DepartmentsImpl {
    public boolean addDepartment(Departments departments);
    public List<Departments> getDepartments();
    public Departments getDepartmentById(int id);
    public boolean addDepartment(String name);

    }
