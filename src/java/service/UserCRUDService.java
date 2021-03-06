/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
    public Users findUserByIndexNumber(long indexNumber) {
        Users user = new Users();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        user= (Users) session.load(Users.class,indexNumber);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        return user;
        
    }
}
