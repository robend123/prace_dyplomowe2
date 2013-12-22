/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Specialization;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.ISpecializationService;
import service.SpecializationService;

/**
 *
 * @author Robson
 */
@ManagedBean
@SessionScoped
public class SpecializationController {
    
    
    private Specialization specialization = new Specialization();
    private Specialization selectedSpecialization;
    private ISpecializationService specializationService= new SpecializationService();
    
    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Specialization getSelectedSpecialization() {
        return selectedSpecialization;
    }

    public void setSelectedSpecialization(Specialization selectedSpecialization) {
        this.selectedSpecialization = selectedSpecialization;
    }
    
    
    /**
     * Creates a new instance of SpecializationController
     */
    public SpecializationController() {
    }
    
    public List<Specialization> createSpecializationList(){
       return specializationService.createSpecializationList();
    }
}
