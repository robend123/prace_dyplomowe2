/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cycle;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Robson
 */
public class CycleService implements ICycleService {
    @Override
     public List<Cycle> createAllCyclesList() {
        List<Cycle> cycles = new ArrayList<Cycle>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        
        //Query query = session.createQuery("select thesis.thesisId, thesis.title, thesis.users.firstName, thesis.users.lastName, thesis.description from Thesis thesis");
        Query query = session.createQuery("from Cycle");

        
        cycles = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

        return cycles;
    }
    
    @Override
    public void saveCycle(Cycle cycle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(cycle);


        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
