package entity;
// Generated Jan 5, 2014 6:06:06 PM by Hibernate Tools 3.2.1.GA



/**
 * ThesisReservation generated by hbm2java
 */
public class ThesisReservation  implements java.io.Serializable {


     private long reservationId;
     private Users users;
     private Thesis thesis;

    public ThesisReservation() {
    }

    public ThesisReservation(long reservationId, Users users, Thesis thesis) {
       this.reservationId = reservationId;
       this.users = users;
       this.thesis = thesis;
    }
   
    public long getReservationId() {
        return this.reservationId;
    }
    
    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public Thesis getThesis() {
        return this.thesis;
    }
    
    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }




}

