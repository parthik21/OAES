package com.example.org.dao;

import com.example.org.bean.Exam;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ExamsDao {
    public boolean addExam(Exam exam) {
        try(Session session = HibernateUtil.getSession()) {
            Transaction t = session.beginTransaction();
            session.save(exam);
            t.commit();
            return true;
        } catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public List<Exam> getAllExams() {
        List<Exam> exams = new ArrayList<>();
        try(Session session = HibernateUtil.getSession();){
            for(final Object exam : session.createQuery("from Exam").list()){
                exams.add((Exam)exam);
            }
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
        }
        return exams;
    }

    public Exam getExamById(int id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Exam.class, id);
        }catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }

    public boolean updateExam(Exam exam){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.update(exam);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public boolean deleteExam(Exam exam){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.delete(exam);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }
}
