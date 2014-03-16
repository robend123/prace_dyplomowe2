/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cycle;
import entity.Specialization;
import entity.Users;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import service.ILoginService;
import service.IUserCRUDService;
import service.LoginService;
import service.UserCRUDService;

/**
 *
 * @author Robson
 */
@ManagedBean
@SessionScoped
public class LoginController {
     private static Users user=new Users();
     private ILoginService loginService=new LoginService();
    // public Users loggedUser = new Users();
    // private Users loggedUser = new Users();
     private String login;
     private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {   
    }
     
    public String login(){
        this.user=loginService.findByLoginAndPassword(login,password);
        if(this.user!=null)//this.loggedUser=this.user; 
            return loginService.login(user);
        else if(user==null) {loginService.setGrowlMessage(); this.user=new Users();}
        return null;
    }
    public void test(){
        Specialization infInzZao = new Specialization("inż","Informatyka","Zaoczne");
        Specialization infMgrZao = new Specialization("mgr","Informatyka","Zaoczne");
        Specialization infMgrDz = new Specialization("mgr","Informatyka","Dzienne");
        Specialization eleInzDz = new Specialization("inż.","Elektronika","Dzienne");
        

                
        Cycle c1= new Cycle("Cykl dla studiów dziennych");
        Cycle c2= new Cycle("Cykl dla wszystksich studiów");
        
        c1.getSpecializations().add(infMgrDz);
        c1.getSpecializations().add(eleInzDz);
        
        c2.getSpecializations().add(infInzZao);
        c2.getSpecializations().add(infMgrDz);
        c2.getSpecializations().add(infMgrZao);
        c2.getSpecializations().add(eleInzDz);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(c1);
        session.save(c2);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
                
      
    }
}
