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

/**
 *
 * @author hafizhme
 */
public class Aplikasi {
    Account account;
    Database connection;
    
    public boolean signIn(String username, String password) {
        List<Account> list;
        list = connection.loadAccount();
        for (Account e : list) {
            if (e.getUsername().equals(username)
                    &&
                e.getPassword().equals(password)) {
                this.account = e;
                return true;
            }
        }
        return false;
    }
    
    public boolean signUp(String username, String password,
            String displayname, String email) {
        if (account == null)
            connection.saveAccount(new Account(username, password, displayname, email));
        else
            return false;
        return true;
    }
    
    public List<Card> getTimeLine() {
        List<Card> list;
        if (account != null)
            list = connection.loadTimeLine(account);
        else
            list = new ArrayList<>();
        
        return list;
    }
    
    public List<Account> findFriend(String key) {
        List<Account> list = new ArrayList<>();
        List<Account> retrieved = connection.loadAccount();
        for (Account e : retrieved) {
            if (e.getDisplayname().contains(key))
                list.add(e);
        }
        
        return list;
    }
    
    public boolean addMedia(String path, List<Account> tag) {
        if (account != null) {
            try {
                FileInputStream fis = new FileInputStream(path);
                Media media = new Media(ImageIO.read(fis));
                account.createMedia(media);
                connection.saveMedia(media, account);
                for (Account e : tag) {
                    connection.saveTag(media, e);
                }
                Card card = new Card(account, media);
                connection.saveCard(card);
                List<Account> follower = connection.loadFollower(account);
                for (Account flw : follower) {
                    connection.saveCardTl(card, flw);
                    flw.addTimeLine(card);
                }
                return true;

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Aplikasi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public List<Media> showMedia(Account account) {
        List<Media> list = connection.loadMedia(account);
        
        return list;
    }
    
    public Media lookMedia(List<Media> medias, int id) {
        Media media = medias.get(id);
        
        List<Account> tag = connection.loadTag(media);
        
        return media;
    }
    
    public boolean createComment(Media media, String text, Account account) {
        Comment comment = new Comment(account, text);
        connection.saveComment(comment, media);
        media.createComment(account, comment);
        
        return true;
    }
    
    public boolean signOut() {
        if (account != null)
            account = null;
        else
            return false;
        return true;
    }
}
