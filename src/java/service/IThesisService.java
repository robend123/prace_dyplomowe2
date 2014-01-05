/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Thesis;
import entity.Users;
import java.util.List;

/**
 *
 * @author Robson
 */
public interface IThesisService {

    void saveThesis(Thesis thesis);
    List<Thesis> createUnconfirmedThesisList();
    List<Thesis> createConfirmedTeacherThesisList(Users user);
    Thesis setDescription();
    void confirmThesis(List<Thesis> selectedThesis);
}
