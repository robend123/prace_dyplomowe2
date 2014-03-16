/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Specialization;
import entity.Users;
import hibernate.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
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
    Specialization preparedSpec= new Specialization();

    public Specialization getPreparedSpec() {
        return preparedSpec;
    }

    public void setPreparedSpec(Specialization preparedSpec) {
        this.preparedSpec = preparedSpec;
    }
    
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
    public void saveSpecialization(){
        specializationService.saveSpecialization(specialization);
        specialization=new Specialization();
    }
    public void updateSpecialization(){
        specializationService.updateSpecialization(preparedSpec);
        preparedSpec=new Specialization();
    }
    
    public void prepareSpecToAction(){
        Specialization spec= new Specialization();
        String ids= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("specializationId").toString();
        long id= Long.parseLong(ids);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        spec=(Specialization)session.get(Specialization.class,id);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
                if(spec!=null){
                  this.preparedSpec=spec;  
                }
        
        //this.userToEdit.setFirstName("blblb");
    }
}
