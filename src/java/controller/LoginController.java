/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
        this.user=loginService.findByLoginAndPassword(user);
        if(this.user!=null)//this.loggedUser=this.user; 
            return loginService.login(user);
        else if(user==null) {loginService.setGrowlMessage(); this.user=new Users();}
        return null;
    }
}
