/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Robson
 */
public class CurrentPlan {
    private long advisorId;
    private Integer thesisLimit = new Integer(0);;
    private Integer confirmedThesises = new Integer(0);
    private Integer reportedThesises = new Integer(0);
    private Users user;
    
    public CurrentPlan(){
        
    }
    
    public CurrentPlan(Integer thesisLimit, Integer confirmedThesises, Integer reportedThesises ){
        this.confirmedThesises=confirmedThesises;
        this.reportedThesises=reportedThesises;
        this.thesisLimit=thesisLimit;
    }
    
     public long getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(long advisorId) {
        this.advisorId = advisorId;
    }

    public Integer getThesisLimit() {
        return thesisLimit;
    }

    public void setThesisLimit(Integer thesisLimit) {
        this.thesisLimit = thesisLimit;
    }

    public Integer getConfirmedThesises() {
        return confirmedThesises;
    }

    public void setConfirmedThesises(Integer confirmedThesises) {
        this.confirmedThesises = confirmedThesises;
    }

    public Integer getReportedThesises() {
        return reportedThesises;
    }

    public void setReportedThesises(Integer reportedThesises) {
        this.reportedThesises = reportedThesises;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
