package com.example.org.bean;

import javax.persistence.*;

@Table
@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int org_id;

    @Column
    private String name;

    @Column
    private String address;

    @OneToOne
    private Employees HR;

    public Organisation(){
    }

    public Organisation(int org_id, String name, String address, Employees employees){
        this.name = name;
        this.address = address;
        this.org_id = org_id;
        this.HR = employees;
    }

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employees getHR() {
        return HR;
    }

    public void setHR(Employees HR) {
        this.HR = HR;
    }
}
