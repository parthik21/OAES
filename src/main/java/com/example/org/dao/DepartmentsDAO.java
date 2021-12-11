package com.example.org.dao;

import com.example.org.bean.Departments;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DepartmentsDAO implements DepartmentsImpl{
    @Override
    public boolean addDepartment(Departments dept) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(dept);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Departments> getDepartments() {
        List<Departments> departments = new ArrayList<>();
        try(Session session = HibernateUtil.getSession()){
            for(final Object employee : session.createQuery("from Departments").list()){
                departments.add((Departments) employee);
            }
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
        }
        return departments;
    }

    @Override
    public Departments getDepartmentById(int id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Departments.class, id);
        }catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean addDepartment(String name){
        Departments departments = new Departments();
        departments.setName(name);
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(departments);
            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

}
