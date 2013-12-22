/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.IUserCRUDService;
import service.UserCRUDService;

/**
 *
 * @author Robson
 */
@ManagedBean
@SessionScoped
public class UserController {

    private Users entityUser = new Users();
    private IUserCRUDService userCRUD = new UserCRUDService();
    private List<Users> administratorsList = new ArrayList<Users>();
    private Users[] selectedUsers;

    public Users getEntityUser() {
        return entityUser;
    }

    public void setEntityUser(Users entityUser) {
        this.entityUser = entityUser;
    }

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

    public IUserCRUDService getUserCRUD() {
        return userCRUD;
    }

    public void setUserCRUD(IUserCRUDService userCRUD) {
        this.userCRUD = userCRUD;
    }

    public Users[] getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(Users[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public List<Users> getAdministratorsList() {
        this.administratorsList = userCRUD.findAllAdministrators();
        return administratorsList;
    }

    public void setAdministratorsList(List<Users> administratorsList) {
        this.administratorsList = administratorsList;
    }

    public void saveUser() {
        userCRUD.saveUser(entityUser);
        this.entityUser = new Users();
    }
    public void editUser(){
        userCRUD.updateUser(selectedUsers);
    }
}
