/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Thesis;
import entity.Users;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Session;
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
   // private List<Users> administratorsList = new ArrayList<Users>();
    private Users[] selectedUsers;
    private Users preparedUser = new Users();
    //private Users userToDelete = new Users();
    private String limit="3";

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
    public Users getPreparedUser() {
        return preparedUser;
    }

    public void setUserToEdit(Users preparedUser) {
        this.preparedUser = preparedUser;
    }

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

//    public List<Users> getAdministratorsList() {
//        this.administratorsList = userCRUD.findAllAdministrators();
//        return administratorsList;
//    }
//
//    public void setAdministratorsList(List<Users> administratorsList) {
//        this.administratorsList = administratorsList;
//    }

    public void saveUser() {
        userCRUD.saveUser(entityUser);
        this.entityUser = new Users();
    }
    public void updateOneUser(){
        userCRUD.updateOneUser(preparedUser);
        this.preparedUser=new Users();
    }
    public List<Users> makeStudentsList(){
        return userCRUD.findAllStudents();
    }
    public List<Users> makeTeachersList(){
        return userCRUD.findAllTeachers();
    }
    public List<Users> makeAdministratorList(){
        return userCRUD.findAllAdministrators();
    }
    
    public void prepareUserToAction(){
       
        Users user = new Users();
        String ids= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId").toString();
        long id= Long.parseLong(ids);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        user=(Users)session.get(Users.class,id);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        this.preparedUser=user;
        //this.userToEdit.setFirstName("blblb");
    }
    public void deleteOneUser(){
        userCRUD.deleteOneUser(preparedUser);
        this.preparedUser=new Users();
    }
    public void setThesisLimit(){
        userCRUD.setThesisLimit(selectedUsers, limit);
    }
}
