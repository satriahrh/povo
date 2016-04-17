/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
        
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ThareeqAD
 */
public class Account implements Serializable{
    private String username;
    private String password;
    private String displayname;
    private String email;
    private int jumlahMedia = 0;
    private int jumlahFollower = 0;
    private int jumlahFollowing = 0;
    private int jumlahCard = 0;
    List<Media> media = new ArrayList();
    private List<Account> following = new ArrayList();
    private List<Account> follower = new ArrayList();
    private List<Card> timeLine = new ArrayList();
    
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
    
    public boolean createMedia(String path){
        Media media = null;
        
        int leng = path.length();
        String format = path.substring(leng-4);
        if (".jpg".equals(format) || ".png".equals(format)) {
            Photo photo = new Photo(path);
            media = photo;
            this.media.add(photo);
        } else if(".mp4".equals(format) || ".avi".equals(format)){
            Video video = new Video(path);
            media = video;
            this.media.add(video);
        } else {
            return false;
        }
        
        for (Account follower : this.follower) {
            follower.addTimeLine(this, media);
        }
        
        jumlahMedia++;
        return true;
    }
    
    public Media getMedia(int id) {
        return this.media.get(id);
    }
    
    public void followFriend(Account account) {
        this.following.add(account);
        account.followedByFriend(this);
        jumlahFollowing++;
    }
    
    public void followedByFriend(Account account) {
        this.follower.add(account);
        jumlahFollower++;
    }
    
    public void addTimeLine(Account account, Media media) {
        Card card = new Card(account,media);
        account.timeLine.add(card);
        account.jumlahCard++;
    }    
    
    public Card getTimeLine(int id) {
        return this.timeLine.get(id);
    }
    
    public String toString(){
        return  "account : " +
                this.username + "\t| " +
                this.displayname + "\t| " +
                this.email + "\t| ";
    }

    /**
     * @return the jumlahMedia
     */
    public int getJumlahMedia() {
        return jumlahMedia;
    }

    /**
     * @return the jumlahFollower
     */
    public int getJumlahFollower() {
        return jumlahFollower;
    }

    /**
     * @return the jumlahFollowing
     */
    public int getJumlahFollowing() {
        return jumlahFollowing;
    }

    /**
     * @return the jumlahCard
     */
    public int getJumlahCard() {
        return jumlahCard;
    }
}