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
    private int jumlahmedia = 0;
    private int jumlahfollower = 0;
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
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void createMedia(String path){
        if (media.length < 10){
            media[jumlah] = path;
            jumlah ++;
        } 
        else
            System.out.println("Tidak Punya Media");
        
    }
    
    public void followfriend((Account p) {
        if (friend.length < 100){
            friend[jumlahfollower] = p;
            jumlahfollower++;
            
        }
        else 
            System.out.println("Tidak Bisa Menambahkan");
        
        
    }
}
