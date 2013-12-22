/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Specialization;
import entity.Thesis;
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
public class ThesisService implements IThesisService {
    @Override
    public void saveThesis(Thesis thesis){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        session.save(thesis);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
    
    @Override
    public List<Thesis> createUnconfirmedThesisList() {
        List<Thesis> unconfirmedThesisList = new ArrayList<Thesis>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        
        //Query query = session.createQuery("select thesis.thesisId, thesis.title, thesis.users.firstName, thesis.users.lastName, thesis.description from Thesis thesis");
        Query query = session.createQuery("select thesis from Thesis thesis where thesis.confirmed=null");

        
        unconfirmedThesisList = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

        return unconfirmedThesisList;
    }
}
