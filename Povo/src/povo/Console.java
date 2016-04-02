/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

import java.util.Scanner;

/**
 *
 * @author hafizhme
 */
public class Console {
    
    Admin admin;
    static Scanner sc = new Scanner(System.in);
    
    public Console() {
        admin = new Admin();
    }
    
    public static Account signUp() {
        Account account = null;
        
        System.out.print("\tusername\t: ");
        String username = sc.next();
        System.out.print("\tdisplayname\t: ");
        String displayname = sc.nextLine();
        System.out.print("\temail\t\t: ");
        String email = sc.next();
        System.out.print("\tpassword\t: ");
        String password = sc.next();
            
        account = new Account();
        account.setUsername(username);
        account.setDisplayname(displayname);
        account.setEmail(email);
        account.setPassword(password);
        
        return account;
    }
    
    public Account signIn() {
        Account account = null;
        
        System.out.println("Username : ");
        String username = sc.next();
        System.out.println("Password : ");
        String password = sc.next();
        
        int i = 0;
        for (i = 0; i < admin.accounts.size(); i++)
            if (admin.accounts.get(i).getUsername().equals(username))
                if (admin.accounts.get(i).getPassword().equals(password))
                    account = admin.accounts.get(i);
                else 
                    System.out.println("Password salah");
        
        if (i == admin.accounts.size())
            System.out.println("Username salah");
        
        return account;
    }
    
}
