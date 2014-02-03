/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ThesisReservation;
import entity.Users;
import java.util.List;

/**
 *
 * @author Robson
 */
public interface IReservationService {

    void makeReservation(ThesisReservation thesisReservation);
    List<ThesisReservation> createReservedThesisList(Users user);
    void cancelReservation(ThesisReservation thesisReservation);
    ThesisReservation prepareReservationToAction();
    void setDefenseDate(ThesisReservation thesisReservation);
}
