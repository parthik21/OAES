package com.example.org.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dept_id;

    @Column
    private String name;

    @OneToMany
    private List<Employees> employeeList;

    public Departments(){

    }

    public Departments(int dept_id, String name, List<Employees> employees)
    {
        this.dept_id = dept_id;
        this.name = name;
        this.employeeList = employees;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employees> getEmployeesList() {
        return employeeList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeeList = employeesList;
    }

}
