/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import FileIO.FileIO;
import Model.Account;
import Model.Admin;
import Model.Card;
import Model.Media;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hafizhme
 */
public class Aplikasi {
    
    Admin admin;
    Account account = null;
    FileIO fio = null;
    static Scanner sc = new Scanner(System.in);
    
    public Aplikasi () throws ClassNotFoundException, IOException {
        fio = new FileIO();
        admin = fio.loadFile();
        if (admin==null){
            admin = new Admin();
            fio.saveFile(admin);
        }
    }
    public void mainMenu() throws IOException, ClassNotFoundException {
        for (;;){
            if (account != null) {
                System.out.println("Main Menu");
                System.out.println("1. Timeline");
                System.out.println("2. Find Friend");
                System.out.println("3. Add Media");
                System.out.println("4. My Media");
                System.out.println("5. Sign Out");             
                switch (sc.nextLine()) {
                    case "1" :
                        showTimeLine();
                        break;
                    case "2" :
                        findFriend();
                        break;
                    case "3" :
                        addMedia();
                        break;
                    case "4" :
                        showMyMedia();
                        break;
                    case "5" :
                        signOut();
                        break;
                    default :
                        System.out.println("Input salah");
                        break;
                }
            } else {
                System.out.println("Main Menu");
                System.out.println("1. Sign In");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                switch (sc.nextLine()) {
                    case "1" :
                        signIn();
                        break;
                    case "2" :
                        signUp();
                        break;
                    case "3" :
                        return;
                    default :
                        System.out.println("Input salah");
                        break;
                }
            }
        }
    }        
    public void signUp() throws IOException, ClassNotFoundException {
        Account account = null;
        admin = fio.loadFile();
        
        System.out.println("username\t: ");
        String username = sc.nextLine();
        System.out.println("displayname\t: ");
        String displayname = sc.nextLine();
        System.out.println("email\t\t: ");
        String email = sc.nextLine();
        System.out.println("password\t: ");
        String password = sc.nextLine();
            
        account = new Account();
        account.setUsername(username);
        account.setDisplayname(displayname);
        account.setEmail(email);
        account.setPassword(password);
     
        admin.createAccount(account);
        fio.saveFile(admin);
        System.out.println("Akun telah dibuat, silakan log in");
    }
    
    public void signIn() throws IOException, ClassNotFoundException {        
        System.out.println("Username : ");
        String username = sc.nextLine();
        System.out.println("Password : ");
        String password = sc.nextLine();
        
        admin = fio.loadFile();
        for (int i = 0; i < admin.accounts.size(); i++)
            if (admin.accounts.get(i).getUsername().equals(username))
                if (admin.accounts.get(i).getPassword().equals(password)) {
                    account = admin.accounts.get(i);
                    break;
                } else {
                    System.out.println("Password salah");
                    break;
                }
        
        if (account == null)
            System.out.println("Username salah");
    }
    
    public void showTimeLine() {
        Card card = null;
        for (int i = 0; i < 10; i++) {
            card = account.getTimeLine(i);
            if (!(card == null))
                card.toString();
            System.out.println("----------------------");                 
        }
    }
    
    public void findFriend() {
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
        if (sc.nextLine().toLowerCase().equals("ya")) {
            System.out.println("Masukkan nomornya : ");
            int idx = sc.nextInt();
            if (idx < foundedAccounts.size()) {
                account.followFriend((foundedAccounts.get(idx)));
                System.out.println(foundedAccounts.get(idx).getDisplayname() + "has been followed");
            }
        }     
    }
    
    public void addMedia() {
        System.out.println("Masukkan alamat media : (*.jpg / *.png / *.mp4 / *.avi)");
        String path = sc.nextLine();
        if (account.createMedia(path)){
            System.out.println("Media berhasil ditambahkan");
        }
    }
    public boolean showMedia(int idx, Account account) {
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
    
    public void showMyMedia() {
        if (account.getMedia(0) != null) {
            int i = 0;
            boolean lanjut = true;
            while (showMedia(i,account) && lanjut) {
                System.out.println("Tampilkan lagi? (Ya/Tidak)");
                lanjut = sc.nextLine().toLowerCase().equals("ya");
            }
        } else {
            System.out.println("Tidak ada media");
        }
    }
    
    public void lookMedia(Media media) {
        System.out.println("----------------");
        System.out.println(media.toString());
        System.out.println("----------------");  
        System.out.println("Mau komen? (ya/tidak)");
        if (sc.nextLine().toLowerCase().equals("YA"))
            makeComment(media);
    }
    
    public void makeComment(Media media) {
        System.out.println("Comment : ");
        media.comments.add(new Comment(account, sc.nextLine()));
    }
    
    public void signOut() throws IOException {
        account = null;
        fio.saveFile(admin);
        System.out.println("Berhasil sign out");
    }
}
