/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Robson
 */
public class Cycle implements Serializable {

    private long cycleId;
    private String name;
    Set Specializations = new HashSet(0);

    public Cycle(){
        
    }
    
    public Cycle(/*long id,*/String name){
        this.name = name;
        //this.cycleId=id;
        
    }
    
    public Long getCycleId() {
        return cycleId;
    }

    public void setCycleId(Long cycleId) {
        this.cycleId = cycleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getSpecializations() {
        return Specializations;
    }

    public void setSpecializations(Set Specializations) {
        this.Specializations = Specializations;
    }
}
