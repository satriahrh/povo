/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * 
 * @author Cha Aska
 */

public abstract class Media implements Serializable {
    private List<Account> tagged;
    public List<Comment> comments = new ArrayList();    

    public Media(){
        tagged = new ArrayList<>();
        comments = new ArrayList<>();
    }
    public void tagPerson(Account account){
        this.tagged.add(account);
    }
    public Account getTagged(int id){
        return this.tagged.get(id);
    } 
    public void createComment(Account account, String text) {
        Comment comment = new Comment(account, text);
        this.comments.add(comment);
    }
    public Comment getComment(int id) {
        return this.comments.get(id);
    }
}
