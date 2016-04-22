/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hafizhme
 */
public class Comment {
    private int id;
    private Account account;
    private String text;
    
    public Comment(Account account, String text) {
        this.account = account;
        this.text = text;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Account getAccount() {
        return this.account;
    }
    
    public String getText() {
        return this.text;
    }
}
