/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * 
 * @author Cha Aska
 */

public abstract class Media {
    private List<Account> tagged = new ArrayList();
    private List<Comment> comments = new ArrayList();
    
    

    public void tagPerson(Account account){
        this.tagged.add(account);
    }
    public Account getTagged(int id){
        return this.tagged.get(id);
    } 
}
