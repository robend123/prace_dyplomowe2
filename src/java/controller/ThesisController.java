/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Specialization;
import entity.Thesis;
import entity.Users;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import service.IThesisService;
import service.ThesisService;

/**
 *
 * @author Robson
 */
@ManagedBean
@RequestScoped
public class ThesisController {

    private Thesis thesis = new Thesis();
    private IThesisService thesisService = new ThesisService();
    private Specialization selectedSpecialization;
    private LoginController loginController = new LoginController();
  

    

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
        this.thesis.setSpecialization(selectedSpecialization);
        thesisService.saveThesis(thesis);
    }
    public List<Thesis> createUnconfirmedThesisList(){
        return thesisService.createUnconfirmedThesisList();
    }
}
