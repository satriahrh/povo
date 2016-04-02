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
 * @author ThareeqAD
 */
public class Account {
    private String username;
    private String password;
    private String displayname;
    private String email;
    private int jumlahMedia = 0;
    private int jumlahFollower = 0;
    private List<Media> media = new ArrayList();
    private List<Account> friend = new ArrayList();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return this.displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void createMedia(String path){
        if ("gambar".equals(path)) {
            Photo photo = new Photo(path);
            this.media.add(photo);
        } else {
            Video video = new Video(path);
            this.media.add(video);
        }
    }
    
    public void followFriend(Account p) {
        this.friend.add(p);
    }
    
    public String toString(){
        return  "account : " +
                this.username + "\t| " +
                this.displayname + "\t| " +
                this.email + "\t| ";
    }
}