/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

/**
 *
 * @author hafizhme
 */
public class Comment {
    private Account account;
    private String text;
    
    public Comment(Account acount, String text) {
        this.account = account;
        this.text = text;
    }
    
    public String getDisplayName() {
        return this.account.getDisplayname();
    }
    
    public String getText() {
        return this.text;
    }
    
    public String toString(){
        return  "comment : " +
                this.getDisplayName() + "\t| " +
                this.getText() + "\t| ";
    }
}
