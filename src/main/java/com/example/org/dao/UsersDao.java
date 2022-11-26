package com.example.org.dao;

import com.example.org.bean.Users;
import com.example.org.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    public boolean addUser(Users users) {
        try(Session session = HibernateUtil.getSession()) {
            Transaction t = session.beginTransaction();
            session.save(users);
            t.commit();
            return true;
        } catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    public Users getUserById(int id) {
        try(Session session = HibernateUtil.getSession()){
            return session.get(Users.class, id);
        }catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }

    public List<Users> getUserByCredentials(String username, String password) {
        try(Session session = HibernateUtil.getSession()) {
            String hql = "FROM Users E WHERE E.username = '" + username + "' and E.password = '"+password+"'";
            List<Users> users = new ArrayList<>();
            for(Object user : session.createQuery(hql).list()) {
                users.add((Users) user);
            }
            return users;
        } catch (HibernateException exception){
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }

    public boolean updateUsers(Users users){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.update(users);
            t.commit();
            return true;
        }catch(HibernateException exception){
            return false;
        }
    }

    public boolean deleteUsers(Users users){
        try(Session session = HibernateUtil.getSession()){
            Transaction t = session.beginTransaction();
            session.delete(users);
            t.commit();
            return true;
        }catch(HibernateException exception){
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

}
