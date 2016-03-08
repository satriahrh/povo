/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

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
    private Media[] media = new Media[10];
    private Account[] friend = new Account[100];

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
        if (this.media.length < 10){
            this.media[this.jumlahMedia] = path;
            this.jumlahMedia ++;
        } 
        else
            System.out.println("Tidak Punya Media");
        
    }
    
    public void followFriend(Account p) {
        if (this.friend.length < 100){
            this.friend[this.jumlahFollower] = p;
            this.jumlahFollower++;
            
        }
        else 
            System.out.println("Tidak Bisa Menambahkan");
        
        
    }
}
