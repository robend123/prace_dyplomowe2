/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cycle;
import java.util.List;

/**
 *
 * @author Robson
 */
public interface ICycleService {
    void saveCycle(Cycle cycle);
    List<Cycle> createAllCyclesList();
    
}
