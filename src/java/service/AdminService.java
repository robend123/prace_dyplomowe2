/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class AdminService implements IAdminService {

    @Override
    public List<Thesis> createUnconfirmedThesisList() {
        List<Thesis> unconfirmedThesisList = new ArrayList<Thesis>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // session.save(user);
        Query query = session.createQuery("select thesis.thesisId, thesis.title ,concat(thesis.users.firstName ,' ', thesis.users.lastName), thesis.description from Thesis thesis");
        unconfirmedThesisList = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

        return unconfirmedThesisList;
    }
    
    @Override
    public void confirmThesis(List<Thesis> selectedThesis){
      //  List<Thesis> unconfirmedThesisList = new ArrayList<Thesis>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // session.save(user);
        for(Thesis thesis: selectedThesis){
            thesis.setConfirmed(true);
            session.update(thesis);
        }
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
   
}
