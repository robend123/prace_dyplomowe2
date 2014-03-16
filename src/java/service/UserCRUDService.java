/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.CurrentPlan;
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
public class UserCRUDService implements IUserCRUDService {

    @Override
    public List<Users> findAllAdministrators() {
        List<Users> administratorsList = new ArrayList<Users>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Query query = session.createQuery("from Users where licence='administrator' ");
        administratorsList = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        return administratorsList;
    }

    @Override
    public void saveUser(Users user) {
        CurrentPlan cr=new CurrentPlan();
        user.setCurrentPlan(cr);
        cr.setUser(user);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano nowego użytkownika ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public void updateOneUser(Users userToUpdate) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(userToUpdate);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dane zaktualizowane ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public List<Users> findAllStudents() {
        List<Users> studentsList = new ArrayList<Users>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Query query = session.createQuery("from Users where licence='student' ");
        studentsList = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        return studentsList;
    }

    @Override
    public List<Users> findAllTeachers() {
        List<Users> teachersList = new ArrayList<Users>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Query query = session.createQuery("from Users where licence='nauczyciel' ");
        teachersList = query.list();
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        return teachersList;
    }
    @Override
    public void deleteOneUser(Users preparedUser){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.delete(preparedUser);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Użytkownik usunięty ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public Users findUserByAlbumNumber(long albumNumber) {
        Users user = new Users();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query query=session.createQuery("select user from Users user where user.albumNumber=:albumNumber").setLong("albumNumber", albumNumber);
        user=(Users) query.uniqueResult();//(Users) session.load(Users.class,albumNumber);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        return user;
        
    }
    @Override
    public void setThesisLimit(Users[] users,String pLimit){
        Integer limit = Integer.parseInt(pLimit);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        for(Users user:users){
         user.getCurrentPlan().setThesisLimit(limit);
         session.update(user);
        }
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        
    }
}
