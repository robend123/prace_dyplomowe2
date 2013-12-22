/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Specialization;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Robson
 */
public class SpecializationService implements ISpecializationService {
    @Override
    public List<Specialization> createSpecializationList(){
        List<Specialization> specializationList=new ArrayList<Specialization>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
       // session.save(user);
        Query query = session.createQuery("from Specialization");
        specializationList=query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
       
        return specializationList;
    }
}
