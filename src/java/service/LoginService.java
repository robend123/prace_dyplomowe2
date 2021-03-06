/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Users;
import hibernate.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Robson
 */
public class LoginService implements ILoginService {

    @Override
    public String login(Users user) {

      /*  try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // session.save(user);
            Query query = session.createQuery("from Users where login = :login and password = :password").setString("login", user.getLogin()).setString("password", user.getPassword());
            user = (Users) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            HibernateUtil.getSessionFactory().close();*/
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zalogowano ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg); 
            
            if (user.getLicence().equalsIgnoreCase("administrator")) {
                return "ADMIN\\confirmTheses.xhtml?faces-redirect=true";
            } else if (user.getLicence().equalsIgnoreCase("student")) {
                return "ADMIN\\confirmTheses.xhtml?faces-redirect=true";
            } else if (user.getLicence().equalsIgnoreCase("nauczyciel")) {
                return "ADMIN\\confirmTheses.xhtml";
            }
            // user= new Users();
           return null;
    }

    @Override
    public void setGrowlMessage() {
     /*   if (user != null) {
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zalogowano ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {*/
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Podano nieprawidłowe dane ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        //}
    }

    @Override
    public Users findByLoginAndPassword(Users user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // session.save(user);
            Query query = session.createQuery("from Users where login = :login and password = :password").setString("login", user.getLogin()).setString("password", user.getPassword());
            user = (Users) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            HibernateUtil.getSessionFactory().close();
        } catch (Exception e) {
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage msg = null;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Podano nieprawidłowe dane ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return user;
    }

    
}
