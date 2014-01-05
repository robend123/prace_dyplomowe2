/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Thesis;
import entity.ThesisReservation;
import entity.Users;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import service.IReservationService;
import service.IThesisService;
import service.IUserCRUDService;
import service.ReservationService;
import service.ThesisService;
import service.UserCRUDService;

/**
 *
 * @author Robson
 */
@ManagedBean
@SessionScoped
public class ReservationController {

    /**
     * Creates a new instance of ReservationController
     */
    private long indexNumber;
    private Users user= new Users();
    private IUserCRUDService userCRUD = new UserCRUDService();
    private IThesisService thesisService = new ThesisService();
    private Thesis thesis = new Thesis();
    private IReservationService reservationService = new ReservationService();
    private ThesisReservation reservation = new ThesisReservation();
   
    
    public Thesis getThesis() {
        return thesis;
    }
  
            
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public long getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(long indexNumber) {
        this.indexNumber = indexNumber;
    }
    public ReservationController() {
    }
    public void findUserByIndexNumber(){
        this.user=userCRUD.findUserByIndexNumber(indexNumber);
    }
    public void setThesisForReservation(){
        this.thesis=thesisService.setDescription();
    }
    public void makeReservation(){
        this.reservation.setThesis(this.thesis);
        this.reservation.setUsers(this.user);
        reservationService.makeReservation(reservation);
    }
}
