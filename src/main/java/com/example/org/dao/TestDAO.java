package com.example.org.dao;

import com.example.org.bean.Test;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    public boolean addTest(Test test) {
        try(Session session = HibernateUtil.getSession()) {
            Transaction t = session.beginTransaction();
            session.save(test);
            t.commit();
            return true;
        } catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try(Session session = HibernateUtil.getSession();){
            for(final Object test : session.createQuery("from Test").list()){
                tests.add((Test)test);
            }
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
        }
        return tests;
    }

    public Test getTestById(int id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Test.class, id);
        }catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }

    public boolean updateTest(Test test){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.update(test);
            t.commit();
            return true;
        }catch(HibernateException exception){
            return false;
        }
    }

    public boolean deleteTest(Test test){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.delete(test);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }
}
