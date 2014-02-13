/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cycle;
import entity.Specialization;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Robson
 */
public interface ISpecializationService {
    void saveSpecialization(Specialization specialization);
    void updateSpecialization(Specialization specialization);
    List<Specialization> createSpecializationList();
    List<Specialization> createSpecializationListByCycleId(Cycle cycle);
}
