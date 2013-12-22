/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Users;
import java.util.List;

/**
 *
 * @author Robson
 */
public interface IUserCRUDService {

    List<Users> findAllAdministrators();
    void saveUser(Users user);
    void updateUser(Users[] selectedUsers);
}
