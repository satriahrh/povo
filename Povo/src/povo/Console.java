/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hafizhme
 */
public class Console {
    
    Admin admin;
    static Scanner sc = new Scanner(System.in);
    
    public Console() {
        
    }
    
    public void mainMenu() {
    
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
    
    public void showTimeLine(Account account) {
        Card card = null;
        for (int i = 0; i < 10; i++) {
            card = account.getTimeLine(i);
            if (!(card == null))
                card.toString();
            System.out.println("----------------------");                 
        }
    }
    
    public void findFriend(Account account) {
        List<Account> foundedAccounts = new ArrayList<>();
        
        System.out.println("Masukkan nama : ");
        String key = sc.nextLine();
        
        int count = 0;
        int i = 0;
        boolean sudahSemua = admin.accounts.get(i) == null;
        while ((count < 10) && (!sudahSemua)) {
            Account foundedAccount = admin.accounts.get(i);
            String displayname = foundedAccount.getDisplayname();
            if (displayname.contains(key)) {
                System.out.println(++count + ". " + displayname);
                foundedAccounts.add(count, foundedAccount);
            }
            
            sudahSemua = admin.accounts.get(++i) == null;
        }
        
        System.out.println("\nDo you wish to follow your friend? (Ya/No)\n");
        if (sc.next().toLowerCase().equals("ya")) {
            System.out.println("Masukkan nomornya : ");
            int idx = sc.nextInt();
            if (idx < foundedAccounts.size()) {
                account.followFriend((foundedAccounts.get(idx)));
                System.out.println(foundedAccounts.get(idx).getDisplayname() + "has been followed");
            }
        }     
    }
    
    public void addMedia(Account account) {
        System.out.println("Masukkan alamat media : (*.jpg / *.png / *.mp4 / *.avi)");
        String path = sc.next();
        if (account.createMedia(path)){
            System.out.println("Media berhasil ditambahkan");
        }
    }
    public boolean show(int idx, Account account) {
        Media media = null;
        for (int i = idx; i < idx+10; i++) {
            media = account.getMedia(i);
            if (media!=null) {
                System.out.println((i+1)+". "+media.toString());
            } else {
                System.out.println("-------sudah semua-------");
                return false;
            }
        }
        
        return true;
    }
    
    public void showMyMedia(Account account) {
        if (account.getMedia(0) != null) {
            int i = 0;
            boolean lanjut = true;
            while (show(i,account) && lanjut) {
                System.out.println("Tampilkan lagi? (Ya/Tidak)");
                lanjut = sc.next().toLowerCase().equals("ya");
            }
        } else {
            System.out.println("Tidak ada media");
        }
    }
    
    public void signOut(Account account) {
        account = null;
        System.out.println("Berhasil sign out");
    }
}
