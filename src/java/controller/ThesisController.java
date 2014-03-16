/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cycle;
import entity.Specialization;
import entity.Thesis;
import entity.Users;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import service.CycleService;
import service.ISpecializationService;
import service.IThesisService;
import service.SpecializationService;
import service.ThesisService;

/**
 *
 * @author Robson
 */
@ManagedBean
@SessionScoped
public class ThesisController {

    private Thesis thesis = new Thesis();
    private IThesisService thesisService = new ThesisService();
    private Specialization selectedSpecialization;
    private LoginController loginController = new LoginController();
    private List<Thesis> selectedThesis= new ArrayList<Thesis>();
    private Thesis thesisToDisplay = new Thesis();
    private List<Thesis> filteredThesis ;
    private CycleService cycleService = new CycleService();
    private Cycle selectedCycle;
    private ISpecializationService specializationService = new SpecializationService();
    private List<Specialization> selectedSpecializations = new ArrayList<Specialization>();
    private Users loggedUser = new Users();

    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }

    public List<Specialization> getSelectedSpecializations() {
        return selectedSpecializations;
    }

    public void setSelectedSpecializations(List<Specialization> selectedSpecializations) {
        this.selectedSpecializations = selectedSpecializations;
    }

    

    public Cycle getSelectedCycle() {
        return selectedCycle;
    }

    public void setSelectedCycle(Cycle selectedCycle) {
        this.selectedCycle = selectedCycle;
    }

    public List<Thesis> getFilteredThesis() {
        return filteredThesis;
    }

    public void setFilteredThesis(List<Thesis> filteredThesis) {
        this.filteredThesis = filteredThesis;
    }

    public Thesis getThesisToDisplay() {
        return thesisToDisplay;
    }

    public void setThesisToDisplay(Thesis thesisToDisplay) {
        this.thesisToDisplay = thesisToDisplay;
    }

    public List<Thesis> getSelectedThesis() {
        return selectedThesis;
    }

    public void setSelectedThesis(List<Thesis> selectedThesis) {
        this.selectedThesis = selectedThesis;
    }

    public Specialization getSelectedSpecialization() {
        return selectedSpecialization;
    }

    public void setSelectedSpecialization(Specialization selectedSpecialization) {
        this.selectedSpecialization = selectedSpecialization;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public IThesisService getThesisService() {
        return thesisService;
    }

    public void setThesisService(IThesisService thesisService) {
        this.thesisService = thesisService;
    }

    /**
     * Creates a new instance of ThesisController
     */
    public ThesisController() {
       
    }
    
    public void saveThesis(){
       /* Users user = new Users();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        Query query = session.createQuery("from Users where firstName='nowy'");
        user = (Users) query.uniqueResult();

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();*/
        this.thesis.setUsers(loginController.getUser());
        this.thesis.setSpecializations(new HashSet(selectedSpecializations));
        thesisService.saveThesis(thesis);
        thesis=new Thesis();
    }
    public List<Thesis> createUnconfirmedThesisList(){
        return thesisService.createUnconfirmedThesisList();
    }
    public List<Thesis> createConfirmedTeacherThesisList(){
        return thesisService.createConfirmedTeacherThesisList(loginController.getUser());
    }
    
    public void setDescription(){
        this.thesisToDisplay=thesisService.setDescription();
    }
    public void confirmThesis(){
        thesisService.confirmThesis(selectedThesis);
    }
    public List<Cycle> createAllCyclesList(){
        return cycleService.createAllCyclesList();
    }
    public List<Specialization> createSpecializationListByCycleId(){
        return specializationService.createSpecializationListByCycleId(selectedCycle);
    }
    public List<Thesis> createConfirmedThesisList(){
        return thesisService.createConfirmedThesisList();
    }
}
