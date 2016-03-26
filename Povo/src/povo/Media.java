/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

/**
 *
 * 
 * @author Cha Aska
 */

public abstract class Media {
    private Account[] tagged;
    private int jumlahTagged = 0;
    private Comment[] comments;
    
    

    public void tagPerson(Account account){
        this.tagged[this.jumlahTagged++] = new Account();
    }
    public Account getTagged(int id){
        return this.tagged[id];
    } 
}
