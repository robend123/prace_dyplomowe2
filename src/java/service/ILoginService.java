/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Users;

/**
 *
 * @author Robson
 */
public interface ILoginService {

    String login(Users user);

    void setGrowlMessage();
    Users findByLoginAndPassword(String login, String password);

 
    
}
