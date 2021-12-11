package com.example.org.dao;

import com.example.org.bean.Employees;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO implements EmployeesImpl{
    @Override
    public boolean addEmployee(Employees emp) {
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.save(emp);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Employees> getEmployees() {
        List<Employees> employees = new ArrayList<>();
        try(Session session = HibernateUtil.getSession();){
            for(final Object employee : session.createQuery("from Employees").list()){
                employees.add((Employees)employee);
            }
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
        }
        return employees;
    }

    @Override
    public Employees getEmployeeByID(int id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Employees.class, id);
        }catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }
}
