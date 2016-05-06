/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hafizhme
 */
public class Aplikasi {

    private List<Account> listAccount;
    private int idAccountActive = -1;
    private Account accountActive = null;
    Database connection;

    public Aplikasi() {
        System.out.println("Memulai aplikasi");
        connection = new Database();
        connection.connect();
        listAccount = connection.retrieveAllAccount();
        connection.retrieveAllMedia(listAccount);
        connection.retrieveAllFriend(listAccount);
    }

    public void addAccount(String username, String password,
            String displayname, String email) {
        System.out.println("add account");
        if (accountActive == null) {
            listAccount.add(new Account(username, password, displayname, email));
            connection.saveAccount(listAccount.get(listAccount.size() - 1));
            System.out.println("User berhasil tersimpan in berhasil");
        } else {
            System.out.println("User sudah login");
        }
    }

    public Account getAccoount(int id) {
        System.out.println("get account");
        System.out.println(listAccount.size());
        if (id >= listAccount.size()) {
            System.out.println("Index lebih tinggi dari size");
            return null;
        }
        Account account = listAccount.get(id);
        
        System.out.println("GetAccount " + account);
        return account;
    }

    public Account signIn(String username, String password) {
        System.out.println("sign in ");
        for (Account e : listAccount) {
            if (e.getUsername().equals(username)
                    && e.getPassword().equals(password)) {
                this.accountActive = e;
                System.out.println("Akun " + this.accountActive + " berhasil sign in");
                return e;
            }
        }
        System.out.println("Username atau password salah");
        return null;
    }

    public boolean signUp(String username, String password,
            String displayname, String email) {
        System.out.println("sing Up");
        if (accountActive == null) {
            for (Account account : listAccount) {
                if (account.getUsername().equals(username)) {
                    System.out.println("akun sudah ada, duplicated username");
                    return false;
                }
            }
            Account account = new Account(username, password, displayname, email);
            connection.saveAccount(account);
            System.out.println("Account berhasil ditambahkan");
            return true;
        } else {
            System.out.println("Ada akun aktif");
            return false;
        }

    }

    public boolean signOut() {
        if (accountActive == null) {
            System.out.println("tidak ada akun yang login");
            return false;
        }
        System.out.println(accountActive + " sign Out");

        accountActive = null;
        System.out.println("Sign out berhasil, aktif akun : " + accountActive);
        return true;
    }

    public void uploadMedia(String path) {
        if (accountActive == null) {
            System.out.println("tidak ada akun yang login");
            return;
        }
        System.out.println("Upload Media " + path);
        if ((path.contains(".jpg")) || (path.contains(".png"))) {
            System.out.println("Upload photo");
            Media media = new Photo(path);
            connection.saveMedia(media, accountActive);
            System.out.println(media + " berhasil uploadMedia");
        } else if ((path.contains(".mp4")) || path.contains(".avi")) {
            System.out.println("Upload Video saat ini tidak tersedia");
        } else {
            System.out.println("file tidak didukung");
        }
    }

    public List<Media> peekUser(int id) {
        if (accountActive == null) {
            System.out.println("tidak ada akun yang login");
            return null;
        }
        System.out.println("peek user id-" + id);
        List<Media> list = new ArrayList<>();
        if (id >= listAccount.size()) {
            System.out.println("Index lebih tinggi dari size");
            return list;
        }
        for (int i = 0; i < listAccount.get(id).numberOfMedias(); i++) {
            Media media = listAccount.get(id).getMedia(id);
            System.out.println("Mendapatkan media" + media);
            list.add(media);
        }
        return list;
    }

    public int getAccount(String user){
        for (Account a : listAccount){
            if (a.getUsername().equals(user))
                return listAccount.indexOf(a);
        }
        return -1;
    }
    
    public void tagPerson(int idMedia, int idAccount) {
        if (accountActive == null) {
            System.out.println("tidak ada akun yang login");
            return;
        }
        if (idAccount >= listAccount.size()) {
            System.out.println("Index lebih tinggi dari size1");
            return;
        }
//        if (idMedia >= listAccount.get(idAccount).numberOfMedias()) {
//            System.out.println("Index lebih tinggi dari size2");
//            return;
//        }
        if (idAccount < 0){
            System.out.println("Tidak ada");
            return;
        }

        Account account = listAccount.get(idAccount);
        Media media = listAccount.get(idAccount).getMedia(idMedia);
        System.out.println("Tag person " + account + " to " + media);

        media.tagPerson(account);
        System.out.println(account + " tagged in " + media);
        connection.saveMedia(media, account);
    }

    public void followFriend(Account following) {
        if (accountActive == null) {
            System.out.println("tidak ada akun yang login");
            return;
        }
        System.out.println("followFriend");
        //Account following = listAccount.get(id);
        System.out.println(accountActive + " going to follow " + following);
        accountActive.followFriend(following);
        System.out.println(accountActive + " is following to " + following);
        connection.saveFollow(accountActive, following);
    }
    
    public void deleteTag(int idAccount, int idMedia, int idTag) {
        System.out.println("Delete media ke-" + idAccount + "." +idMedia + "." + idTag);
        if (idAccount >= listAccount.size()) {
            System.out.println("Index Account lebih tinggi dari size");
            return;
        }
        if (idMedia >= listAccount.get(idAccount).numberOfMedias()) {
            System.out.println("Index Media lebih tinggi dari size");
            return;
        }
        if (idTag >= listAccount.get(idAccount).getMedia(idMedia).numberOfTags()) {
            System.out.println("Index Tag lebih tinggi dari size");
            return;
        }
        Account account = listAccount.get(idAccount);
        Media media = account.getMedia(idMedia);
        media.removeTag(idTag);
        System.out.println(account + " berhasil untag dari " + media);
        connection.removeTag(account, media);
        
        
    }
    public void deleteMedia(int idAccount, int idMedia) {
        System.out.println("Delete media ke-" + idAccount + "." +idMedia);
        if (idAccount >= listAccount.size()) {
            System.out.println("Index lebih tinggi dari size");
            return;
        }
        if (idMedia >= listAccount.get(idAccount).numberOfMedias()) {
            System.out.println("Index lebih tinggi dari size");
            return;
        }
        Account account = listAccount.get(idAccount);
        Media media = account.getMedia(idMedia);
        for (int i = 0; i < media.numberOfTags(); i++) {
            deleteTag(idAccount, idMedia, i);
        }
        account.removeMedia(idMedia);
        System.out.println(media + " berhasil untag dari " + account);
        connection.removeMedia(media);
    }
    
    public void deleteFriend(int idFollower, int idFollowing) {
        System.out.println("Delete friend ke-" + idFollower + "." +idFollowing);
        if (idFollower >= listAccount.size()) {
            System.out.println("Index lebih tinggi dari size");
            return;
        }
        if (idFollowing >= listAccount.size()) {
            System.out.println("Index lebih tinggi dari size");
            return;
        }
        Account account = listAccount.get(idFollower);
        Account following = listAccount.get(idFollowing);
        account.removeFriends(idFollower);
        System.out.println(following + " dihapus dari following " + account);
        connection.removeFriend(account, following);
    }
    
    public void deleteAccount(int idAccount) {
        System.out.println("Delete account ke-" + idAccount);
        if (idAccount >= listAccount.size()) {
            System.out.println("Index lebih tinggi dari size");
            return;
        }
        for (int i = 0; i < listAccount.size(); i++) {
            Account account = listAccount.get(i);
            for (int j = 0; j < account.numberOfMedias(); j++) {
                Media media = account.getMedia(j);
                for (int k = 0; k < media.numberOfTags(); k++) {
                    deleteTag(i,j,k);
                }
                deleteMedia(i,j);
            }
            for (int j = 0; j < account.numberOfFriends(); j++) {
                deleteFriend(i,j);
            }
        }
        System.out.println("Akun " + listAccount.remove(idAccount) + " berhasil dihapus");
    }
    
    public DefaultTableModel getUsername(){
        
        return connection.getData();
    }
      public DefaultTableModel getMedia(){
        return connection.getimage();
    }

}
 
