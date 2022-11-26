package com.example.org.dao;

import com.example.org.bean.Student;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student student) {
        try(Session session = HibernateUtil.getSession()) {
            Transaction t = session.beginTransaction();
            session.save(student);
            t.commit();
            return true;
        } catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try(Session session = HibernateUtil.getSession();){
            for(final Object employee : session.createQuery("from Student").list()){
                students.add((Student)employee);
            }
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
        }
        return students;
    }

    public Student getStudentById(int id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Student.class, id);
        }catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }

    public boolean updateStudent(Student student){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.update(student);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteStudent(Student student){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.delete(student);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }


    }

}
