/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cycle;
import entity.Specialization;
import entity.Thesis;
import entity.ThesisHistory;
import entity.Users;
import hibernate.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import service.CycleService;
import service.ICycleService;
import service.ISpecializationService;

/**
 *
 * @author Robson
 */
@ManagedBean
@SessionScoped
public class CycleController{

    private Cycle cycle = new Cycle();
    private ICycleService cycleService = new CycleService();
    private List<Specialization> selectedSpecializations = new ArrayList<Specialization>();
    private Cycle preparedCycle = new Cycle();

    public Cycle getPreparedCycle() {
        return preparedCycle;
    }

    public void setPreparedCycle(Cycle preparedCycle) {
        this.preparedCycle = preparedCycle;
    }

    public List<Specialization> getSelectedSpecializations() {
        return selectedSpecializations;
    }

    public void setSelectedSpecializations(List<Specialization> selectedSpecializations) {
        this.selectedSpecializations = selectedSpecializations;
    }

    public CycleController() {
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public void saveCycle() {
        cycle.setSpecializations(new HashSet(selectedSpecializations));
        cycleService.saveCycle(cycle);
        cycle = new Cycle();
    }

    public List<Cycle> createAllCyclesList() {
        return cycleService.createAllCyclesList();
    }

    public void prepareCycleToAction() {

        Cycle cycle = new Cycle();
        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cycleId").toString();
        long id = Long.parseLong(ids);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        cycle = (Cycle) session.get(Cycle.class, id);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
        this.preparedCycle = cycle;
        //this.userToEdit.setFirstName("blblb");
    }

    public void closeCycle() {
        Set<Specialization> specializations = preparedCycle.getSpecializations();
        Map<String, String> mapForCheckingCopies = new HashMap<String, String>();
        Set<ThesisHistory> thesisesInHistory = new HashSet(0);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        for (Specialization spec : specializations) {
            Set<Thesis> thesises = spec.getManyThesises();
            for (Thesis thesis : thesises) {
                if (thesis.getReserved() == null || thesis.getReserved() == false) {
                    ThesisHistory thesisHistory = new ThesisHistory();
                    thesisHistory.setUsers(thesis.getUsers());
                    thesisHistory.setTitle(thesis.getTitle());
                    thesisHistory.setDescription(thesis.getDescription());
                    if (!mapForCheckingCopies.containsKey(thesis.getTitle())) {
                        mapForCheckingCopies.put(thesis.getTitle(), thesis.getDescription());
                        thesisesInHistory.add(thesisHistory);
                        thesis.getSpecializations().clear();
                        session.delete(thesis);
                    }
                }
            }
        }

        for (ThesisHistory thesis : thesisesInHistory) {
            session.save(thesis);
        }

        
        
        session.getTransaction().commit();
        session.close();
        HibernateUtil.getSessionFactory().close();

    }
}
