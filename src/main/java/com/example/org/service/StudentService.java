package com.example.org.service;

import com.example.org.bean.Student;
import com.example.org.dao.StudentDAO;

import java.util.List;

public class StudentService {
    StudentDAO dao = new StudentDAO();
    public boolean addStudent(Student student) {
        return dao.addStudent(student);
    }
    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }
    public Student getStudentById(int id) {
        return dao.getStudentById(id);
    }
    public boolean updateStudent(Student student){return dao.updateStudent(student);}
    public boolean deleteStudent(Student student){return dao.deleteStudent(student);}
    public List<Student> getStudents(){
        return getAllStudents();
    }

    public String uploadStudent(String first_name,
                                  String middle_name,
                                  String last_name,
                                  String email,
                                  String contact_number,
                                  String enrollment_number,
                                  String address,
                                  String username,
                                  String password) {

        System.out.println("here!");

        StudentService service = new StudentService();
        Student student = Student.factory();

        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        student.setAddress(address);
        student.setContact_number(contact_number);
        student.setEnrollment_number(enrollment_number);
        student.setUsername(username);
        student.setPassword(password);
        student.setEmail(email);
        student.setMiddle_name(middle_name);

        System.out.println("here!");
        boolean result = service.addStudent(student);
        String n = new String("New Student Created!");
        if(!result)
            n = new String("Error Creating student!");
        return n;
    }

    public String deleteStudent(int id){
        Student student = getStudentById(id);
        boolean result = deleteStudent(student);
        String n = new String("Student Deleted Successfully!");
        if(!result)
            n = new String("Student could not be deleted!");
        return n;
    }

//    public String myProfile(int id){
//        Student student = getStudentById(id);
//
//        return n;
//    }
}
