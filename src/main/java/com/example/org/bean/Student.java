package com.example.org.bean;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;

    @Column(unique = true)
    @NotNull
    private String enrollment_number;

    @Column
    @NotNull
    private String first_name;

    @Column
    @NotNull
    private String middle_name;

    @Column
    @NotNull
    private String last_name;

    @Column(unique = true)
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String contact_number;

    @Column
    @NotNull
    private String address;

    @Column(unique = true)
    @NotNull
    private String email;

    private Student(){
    }

    private Student(int student_id, String enrollment_number,
                   String first_name, String middle_name,
                   String last_name, String username, String password,
                   String contact_number, String address, String email) {
        this.student_id = student_id;
        this.enrollment_number = enrollment_number;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.contact_number = contact_number;
        this.address = address;
        this.email = email;
    }

    public static Student factory(int student_id, String enrollment_number,
                           String first_name, String middle_name,
                           String last_name, String username, String password,
                           String contact_number, String address, String email) {
        return new Student( student_id, enrollment_number,
                 first_name,  middle_name,
                 last_name,  username,  password,
                 contact_number,  address,  email);
    }

    public static Student factory() {
        return new Student();
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getEnrollment_number() {
        return enrollment_number;
    }

    public void setEnrollment_number(String enrollment_number) {
        this.enrollment_number = enrollment_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
