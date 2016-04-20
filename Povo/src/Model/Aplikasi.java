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
        System.out.println("1. Account");
        System.out.println("2. Admin");
        if (sc.nextLine().equals("1"))
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
                            showMedia(account);
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
        else
            System.out.println("Belum tersedia saat ini");
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
     
        if (admin.createAccount(account)) {
            fio.saveFile(admin);
            System.out.println("Akun telah dibuat, silakan log in");
        } else {
            System.out.println("Terdapat akun dengan username sama");
        }
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
        showTimeLine(0);
    }
    public void showTimeLine(int idx) {
        Card card = null;
        int i = idx;
        for (; i < 10; i++) {
            if (i == account.getJumlahCard()){
                if (i==0)
                    System.out.println("Anda belum mempunyai timeline,\n"
                            + "coba follow beberapa teman untuk\n"
                            + "mendapatkan timeline");
                else
                    System.out.println("-------sudah semua-------");
                return;
            }
            card = account.getTimeLine(i);
            System.out.println((i+1)+". "+card.toString());
        }
        System.out.println("Lagi ? (ya/tidak)");
        if (sc.nextLine().toLowerCase().equals("ya"))
            showTimeLine(i);
    }
    
    public void findFriend() {
        List<Account> foundedAccounts = new ArrayList<>();
        
        System.out.println("Masukkan nama : ");
        String key = sc.nextLine();
        
        int count = 0;
        int i = 0;
        boolean sudahSemua = admin.getJumlahAccount() == i;
        while ((count < 10) && (!sudahSemua)) {
            Account foundedAccount = admin.accounts.get(i);
            String displayname = foundedAccount.getDisplayname();
            if (displayname.contains(key)) {
                System.out.println(++count + ". " + displayname);
                foundedAccounts.add(foundedAccount);
            }
            
            sudahSemua = admin.getJumlahAccount() == ++i;
        }
        if (count==0)
            System.out.println("Tidak ada akun ditemukan");
        else {
            System.out.println("\nDo you wish to follow your friend? (Ya/No)\n");
            if (sc.nextLine().toLowerCase().equals("ya")) {
                System.out.println("Masukkan nomornya : ");
                int idx = Integer.parseInt(sc.nextLine())-1;
                if (idx < foundedAccounts.size()) {
                    this.account.followFriend((foundedAccounts.get(idx)));
                    System.out.println(foundedAccounts.get(idx).getDisplayname() + " has been followed");
                } else {
                    System.out.println("Input Salah");
                }
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
    public void showMedia(Account account) {
        showMedia(account, 0);
    }
    public void showMedia(Account account, int idx) {
        Media media = null;
        int i = idx;
        for (; i < 10; i++) {
            if (i == account.getJumlahMedia()){
                if (i==0)
                    System.out.println("Anda belum mempunyai timeline,\n"
                            + "coba follow beberapa teman untuk\n"
                            + "mendapatkan timeline");
                else
                    System.out.println("-------sudah semua-------");
                return;
            }
            media = account.getMedia(i);
            System.out.println((i+1)+". "+media.toString());
        }
        System.out.println("Lagi ? (ya/tidak)");
        if (sc.nextLine().toLowerCase().equals("ya"))
            showMedia(account, i);
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
