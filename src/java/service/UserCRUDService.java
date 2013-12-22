/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Users;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Robson
 */
public class UserCRUDService implements IUserCRUDService {
    
    
    @Override
    public List<Users> findAllAdministrators(){
        List<Users> administratorsList=new ArrayList<Users>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
       
        Query query = session.createQuery("from Users where licence='administrator' ");
        administratorsList=query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        return administratorsList;
    }
    
    @Override
    public void saveUser(Users user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        session.save(user);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
     
    @Override
    public void updateUser(Users[] selectedUsers){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       for(Users user: selectedUsers)
        session.update(user);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
