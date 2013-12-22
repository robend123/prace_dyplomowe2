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
public interface IAdminService {

    List<Thesis> createUnconfirmedThesisList();
    void confirmThesis(List<Thesis> selectedThesis);
}
