/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Thesis;
import entity.ThesisReservation;
import entity.Users;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;

import org.hibernate.Session;

/**
 *
 * @author Robson
 */
public class ReservationService implements IReservationService {

    @Override
    public void makeReservation(ThesisReservation thesisReservation) {
        if (checkIfUserHasReservation(thesisReservation.getUsers()) == false) {
            Thesis thesis = thesisReservation.getThesis();
            thesis.setReserved(true);
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(thesis);
            session.save(thesisReservation);

            session.getTransaction().commit();
            session.close();
            HibernateUtil.getSessionFactory().close();
            FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zarezerwowano temat ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podany student posiada już rezerwację ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    @Override
    public List<ThesisReservation> createReservedThesisList(Users user) {
        List<ThesisReservation> reservedThesisList = new ArrayList<ThesisReservation>();
        long userIds = user.getUserId();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("select thesisReservation from ThesisReservation thesisReservation where thesisReservation.thesis.users.userId=:userIds").setLong("userIds", userIds);
        reservedThesisList = query.list();

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        return reservedThesisList;
    }

    public boolean checkIfUserHasReservation(Users user) {
        List<ThesisReservation> reservedThesisList = new ArrayList<ThesisReservation>();
        long userIds = user.getUserId();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("select thesisReservation from ThesisReservation thesisReservation where thesisReservation.users.userId=:userIds").setLong("userIds", userIds);
        reservedThesisList = query.list();

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        if (reservedThesisList.size() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void cancelReservation(ThesisReservation thesisReservation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Thesis thesis = thesisReservation.getThesis();
        thesis.setReserved(false);

        session.delete(thesisReservation);
        session.update(thesis);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        FacesMessage msg = null;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rezerwacja anulowana ", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public ThesisReservation prepareReservationToAction() {
        ThesisReservation thesisReservation = new ThesisReservation();
        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("reservationId").toString();
        long id = Long.parseLong(ids);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        thesisReservation = (ThesisReservation) session.get(ThesisReservation.class, id);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

        return thesisReservation;
    }
}
