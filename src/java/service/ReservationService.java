/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ThesisReservation;
import hibernate.HibernateUtil;

import org.hibernate.Session;

/**
 *
 * @author Robson
 */
public class ReservationService implements IReservationService {
    @Override
    public void makeReservation(ThesisReservation thesisReservation){
       Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       
       session.save(thesisReservation);
       
       session.getTransaction().commit();
       session.close();
       HibernateUtil.getSessionFactory().close();
       
    }
}
