package com.example.org.dao;

import com.example.org.bean.Organisation;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrganisationDAO implements OrganisationImpl{
    @Override
    public boolean addOrganisation(Organisation organisation) {
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.save(organisation);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Organisation> getOrganisations() {
        List<Organisation> organisations = new ArrayList<>();
        try(Session session = HibernateUtil.getSession();){
            for(final Object object : session.createQuery("from Organisation").list()){
                organisations.add((Organisation) object);
            }
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
        }
        return organisations;
    }

    @Override
    public Organisation getOrganisationById(int id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Organisation.class, id);
        }catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }
}
