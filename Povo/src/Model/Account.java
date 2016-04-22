/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hafizhme
 */
public class Account {
    private String username;
    private String password;
    private String displayname;
    private String email;
    private List<Media> medias = new ArrayList<Media>();
    private List<Account> following = new ArrayList<>();
    private List<Account> follower = new ArrayList<>();
    private List<Card> timeline = new ArrayList<>();
    
    public Account(String username,
            String password,
            String displayname,
            String email
            ) {
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getEmail() {
        return email;
    }
        
    public void createMedia(Media media) {
        this.medias.add(media);
    }
    
    public Media getMedia(int id) {
        return this.medias.get(id);      
    }
    
    public void followFriend(Account account) {
        this.following.add(account);
        account.followedByFriend(this);        
    }
    
    public void followedByFriend(Account account) {
        this.follower.add(account);
    }
    
    public void addTimeLine(Card card) {
        this.timeline.add(card);
    }
    
    public String toString() {
        return this.username + " | " +
                this.displayname + " | " +
                this.displayname + " | " +
                this.email;
    }
    
}
