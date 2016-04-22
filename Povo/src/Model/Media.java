/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hafizhme
 */
public class Media {
    private int id;
    private final BufferedImage content;
    private List<Account> tags = new ArrayList<Account>();
    public List<Comment> comments = new ArrayList<Comment>();
    
    public Media(BufferedImage content) {
        this.content = content;
    }
    
    public Media(int id, BufferedImage content) {
        this.id = id;
        this.content = content;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public BufferedImage getContent() {
        return this.content;
    }
    
    public void tagPerson(Account account) {
        this.tags.add(account);
    }
    
    public Account getTag(int id) {
        return this.tags.get(id);
    }
    
    public void createComment(Account account, Comment comment) {
        this.comments.add(comment);
    }
    
    public Comment getComment(int id){
        return this.comments.get(id);
    }
    
    public String toString() {
        return this.content.toString();
    }
    
}
