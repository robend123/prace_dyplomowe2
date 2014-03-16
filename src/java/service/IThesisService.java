/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Thesis;
import entity.ThesisHistory;
import entity.Users;
import java.util.List;

/**
 *
 * @author Robson
 */
public interface IThesisService {

    void saveThesis(Thesis thesis);
    List<Thesis> createConfirmedThesisList();
    List<Thesis> createUnconfirmedThesisList();
    List<Thesis> createConfirmedTeacherThesisList(Users user);
    List<ThesisHistory> createThesisHistoryList(Users user);
    void importThesisFromHistory(Thesis thesis);
    Thesis setDescription();
    void confirmThesis(List<Thesis> selectedThesis);
}
