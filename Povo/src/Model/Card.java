/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author hafizhme
 */
public class Card implements Serializable {
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
