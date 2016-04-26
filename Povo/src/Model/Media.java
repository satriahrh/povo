/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hafizhme
 */
public abstract class Media {
    private int id;
    final String path;
    private List<Account> tags = new ArrayList<Account>();
    private int jumlahTag = tags.size();
    
    public Media(String path) {
        this.path = path;
        this.fileToContent();
    }
    
    public Media(int id, String path) {
        this.id = id;
        this.path = path;
        this.fileToContent();
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getPath(){
        return this.path;
    }
       
    public Account getTag(int id) {
        return this.tags.get(id);
    }
    
    public void tagPerson(Account account) {
        this.tags.add(account);
    }
    
    public int numberOfTags() {
        return this.jumlahTag;
    }
    
    public void removeTag(int id) {
        this.tags.remove(id);
    }
       
    public String toString() {
        return this.path;
    }
    
    public abstract void fileToContent();

}
