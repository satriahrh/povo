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
public class Card {
    private int id;
    private Account account;
    private Media media;
    
    public Card(Account account, Media media) {
        this.account = account;
        this.media = media;
    }

    public Card(int id, Account account, Media media) {
        this.account = account;
        this.media = media;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    
    public String toString() {
        return this.getAccount().getDisplayname() + " | " +
                this.getMedia().toString() + " | ";
    }

    public Account getAccount() {
        return account;
    }

    public Media getMedia() {
        return media;
    }
    
}
