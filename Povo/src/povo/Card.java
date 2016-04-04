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
public class Card {
    private Account account;
    private Media media;
    
    public Card(Account account, Media media) {
        this.account = account;
        this.media = media;
    }
    
    public String toString() {
        return this.account.getDisplayname() + "\n" + 
               this.media.toString() + "\n";
    }
}
