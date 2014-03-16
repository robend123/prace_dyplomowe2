/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Robson
 */
public class ThesisHistory implements Serializable{
    
     private long thesisId;
     private Users users;
     private String title;
     private String description;
     
     public ThesisHistory(){
         
     }
     
     public long getThesisId() {
        return thesisId;
    }

    public void setThesisId(long thesisId) {
        this.thesisId = thesisId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     
}
