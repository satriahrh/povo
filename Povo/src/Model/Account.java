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
    private List<Account> friends = new ArrayList<>();
    private int jumlahMedias = medias.size();
    private int jumlahFriends = medias.size();
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public int numberOfMedias() {
        return this.jumlahMedias;
    }

    public int numberOfFriends() {
        return this.jumlahFriends;
    }
        
    public void createMedia(Media media) {
        this.medias.add(media);
    }
    
    public Media getMedia(int id) {
        return this.medias.get(id);      
    }
    
    public void followFriend(Account account) {
        this.friends.add(account);
    }
    
    public void removeFriends(int id) {
        friends.remove(id);
    }
    
    public void removeMedia(int id) {
        for (int i = 0; i < medias.get(id).numberOfTags(); i++) {
            medias.get(id).removeTag(i);
        }
        medias.remove(id);
    }
       
    public String toString() {
        return this.getUsername() + " | " +
                this.getDisplayname() + " | " +
                this.getDisplayname();
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

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
}
