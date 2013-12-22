/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Thesis;
import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Session;
import service.AdminService;
import service.IAdminService;

/**
 *
 * @author Robson
 */
@ManagedBean
@SessionScoped
public class AdminController {

    private List<Thesis> selectedThesis=new ArrayList();
    private IAdminService adminService = new AdminService();
    private Thesis thesisToDisplay = new Thesis();

    public Thesis getThesisToDisplay() {
        return thesisToDisplay;
    }

    public void setThesisToDisplay(Thesis thesisToDisplay) {
        this.thesisToDisplay = thesisToDisplay;
    }
    public AdminController() {
    }
    
    public List<Thesis> getSelectedThesis() {
        return selectedThesis;
    }

    public void setSelectedThesis(List<Thesis> selectedThesis) {
        this.selectedThesis = selectedThesis;
    }
    
    public void confirmThesis(){
        adminService.confirmThesis(selectedThesis);
    }
    public void setDescription(){
        Thesis thesis = new Thesis();
        String ids= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("thesisId").toString();
        long id= Long.parseLong(ids);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        thesis=(Thesis)session.get(Thesis.class,id);
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        this.thesisToDisplay=thesis;
    }
}
