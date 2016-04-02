/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hafizhme
 */
public class Admin {
   
    protected List<Account> accounts = new ArrayList();
    private int jumlahAccount = 0;
    
    protected void createAccount(Account account) {
        this.accounts.add(account);
        this.jumlahAccount++;
    }
    
    protected void deleteAccount(Account account) {
        this.accounts.remove(account);
    }
    
    protected void deleteMedia(Account account, int media) {
        
    }
    
    protected void deleteComment(Account account, int media, int comment) {
        
    }
}
