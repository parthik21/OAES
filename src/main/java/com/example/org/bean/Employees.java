package com.example.org.bean;

import javax.persistence.*;

@Table
@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn
    private Departments department;

    @Column
    private String username;

    @Column
    private String password;

    public Employees(){
    }

    public Employees(int emp_id,String name, Departments department,String username, String password){
        this.emp_id = emp_id;
        this.department = department;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getEmp_id(){return emp_id;}
    public Departments getDepartment(){return department;}
    public String getName(){return name;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}

    public void setEmp_id(int emp_id){
        this.emp_id = emp_id;
    }
    public void setDepartment(Departments departments){
        this.department = departments;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUsername(String username){
        this.username  = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
