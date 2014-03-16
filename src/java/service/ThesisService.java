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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Robson
 */
public class ThesisService implements IThesisService {
    @Override
    public void saveThesis(Thesis thesis){
        Users user= thesis.getUsers();
        user.getCurrentPlan().setReportedThesises(user.getCurrentPlan().getReportedThesises()+1);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        session.save(thesis);
        session.update(user);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano temat ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
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

    @Override
    public List<Thesis> createConfirmedTeacherThesisList(Users user) {
        List<Thesis> teacherThesisList = new ArrayList<Thesis>();
        long userIds=user.getUserId();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        
        //Query query = session.createQuery("select thesis.thesisId, thesis.title, thesis.users.firstName, thesis.users.lastName, thesis.description from Thesis thesis");
        Query query = session.createQuery("select thesis from Thesis thesis where thesis.users.userId=:userIds and thesis.confirmed=true and (thesis.reserved=null or thesis.reserved=false)").setLong("userIds", userIds);

        
        teacherThesisList = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

        return teacherThesisList;
    }
    @Override
     public List<Thesis> createConfirmedThesisList() {
        List<Thesis> thesisList = new ArrayList<Thesis>();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        
        //Query query = session.createQuery("select thesis.thesisId, thesis.title, thesis.users.firstName, thesis.users.lastName, thesis.description from Thesis thesis");
        Query query = session.createQuery("select thesis from Thesis thesis where thesis.confirmed=:confirmed").setBoolean("confirmed", true);

        
        thesisList = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

        return thesisList;
    }
    
    @Override
    public Thesis setDescription() {
         Thesis thesisToDisplay = new Thesis();
        String ids= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("thesisId").toString();
        long id= Long.parseLong(ids);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        thesisToDisplay=(Thesis)session.get(Thesis.class,id);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
                
        return thesisToDisplay;
    }
    @Override
    public void confirmThesis(List<Thesis> selectedThesis){
      //  List<Thesis> unconfirmedThesisList = new ArrayList<Thesis>();
        Users users= new Users();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // session.save(user);
        for(Thesis thesis: selectedThesis){
            thesis.setConfirmed(true);
            
            session.update(thesis);
            users=thesis.getUsers();
            users.getCurrentPlan().setConfirmedThesises(users.getCurrentPlan().getConfirmedThesises()+1);
            session.update(users);
        }
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tematy potwiedzone ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
