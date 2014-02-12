/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Thesis;
import entity.ThesisReservation;
import entity.Users;
import java.util.List;
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
    private long albumNumber;
    private Users user= new Users();
    private IUserCRUDService userCRUD = new UserCRUDService();
    private IThesisService thesisService = new ThesisService();
    private Thesis thesis = new Thesis();
    private IReservationService reservationService = new ReservationService();
    private ThesisReservation reservation = new ThesisReservation();
    

    public ThesisReservation getReservation() {
        return reservation;
    }

    public void setReservation(ThesisReservation reservation) {
        this.reservation = reservation;
    }
    private LoginController loginController = new LoginController();
   
    
    public Thesis getThesis() {
        return thesis;
    }
  
            
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public long getAlbumNumber() {
        return albumNumber;
    }

    public void setAlbumNumber(long albumNumber) {
        this.albumNumber = albumNumber;
    }
    public ReservationController() {
    }
    public void findUserByAlbumNumber(){
        this.user=userCRUD.findUserByAlbumNumber(albumNumber);
    }
    public void setThesisForReservation(){
        this.thesis=thesisService.setDescription();
    }
    public void makeReservation(){
        this.reservation.setThesis(this.thesis);
        this.reservation.setUsers(this.user);
        this.reservation.getThesis().setReserved(true);
        reservationService.makeReservation(reservation);
        this.reservation=new ThesisReservation();
    }
    public List<ThesisReservation> createReservedThesisList(){
        return reservationService.createReservedThesisList(loginController.getUser());
    }
    public void prepareReservationToAction(){
        this.reservation=reservationService.prepareReservationToAction();
    }
    public void cancelReservation(){
        reservationService.cancelReservation(reservation);
        this.reservation=new ThesisReservation();
    }
    public void setDefenseDate(){
        reservationService.setDefenseDate(reservation);
        this.reservation=new ThesisReservation();
    }
}
