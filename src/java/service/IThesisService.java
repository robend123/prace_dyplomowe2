/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Thesis;
import java.util.List;

/**
 *
 * @author Robson
 */
public interface IThesisService {

    void saveThesis(Thesis thesis);
    List<Thesis> createUnconfirmedThesisList();
}
