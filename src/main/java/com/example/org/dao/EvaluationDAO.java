package com.example.org.dao;

import com.example.org.bean.Evaluation;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EvaluationDAO {

    public boolean addEvaluation(Evaluation evaluation) {
        try(Session session = HibernateUtil.getSession()) {
            Transaction t = session.beginTransaction();
            session.save(evaluation);
            t.commit();
            return true;
        } catch(HibernateException exception) {
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public List<Evaluation> getAllEvaluations(int student_id, int exam_id) {
        List<Evaluation> evaluations = new ArrayList<>();
        try(Session session = HibernateUtil.getSession()) {
            for(final Object evaluation : session.createQuery("from Evaluation").list()) {
                Evaluation evaluation1 = (Evaluation) evaluation;
                if ((evaluation1.getStudent().getStudent_id() == student_id) &&
                (evaluation1.getTest().getExam().getExam_id() == exam_id)) {
                    evaluations.add((Evaluation) evaluation);
                }
            }
        } catch(HibernateException exception) {
            System.out.println(exception.getLocalizedMessage());
        }
        return evaluations;
    }

    public Evaluation getEvaluationById(int id) {
        try(Session session = HibernateUtil.getSession()) {
            return session.get(Evaluation.class, id);
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }

    public boolean updateEvaluation(Evaluation test){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.update(test);
            t.commit();
            return true;
        }catch(HibernateException exception){
            return false;
        }
    }

    public boolean deleteEvaluation(Evaluation evaluation){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.delete(evaluation);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public List<Evaluation> getPendingEvaluations() {
        String hql = "FROM Evaluation E WHERE E.evaluated is null";
        Session session = HibernateUtil.getSession();
        List<Evaluation> evaluations = new ArrayList<>();
        for(final Object object : session.createQuery(hql).list()) {
            Evaluation evaluation = (Evaluation) object;
            evaluations.add(evaluation);
        }
        return evaluations;
    }
}
