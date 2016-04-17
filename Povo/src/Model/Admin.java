/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hafizhme
 */
public class Admin implements Serializable {
   
    private String username = "usernameadmin";
    private String password = "passwordadmin";
    public List<Account> accounts = new ArrayList();
    private int jumlahAccount = 0;
    
    public void createAccount(Account account) {
        this.accounts.add(account);
        this.jumlahAccount++;
    }
    
    public void deleteAccount(Account account) {
        this.accounts.remove(account);
    }
    
    public void deleteMedia(Account account, int media) {
        account.media.remove(media);
    }
    
    public void deleteComment(Account account, int media, int comment) {
        account.media.get(media).comments.remove(comment);
    }
}
