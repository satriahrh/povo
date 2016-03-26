/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;


/**
 *
 * @author Cha Aska
 */
public class Photo extends Media{
    private String content;
    
    public Photo(String content){
        this.content = content;
    }
    
    public String getContent(){
        return this.content;
    }
    
    public String toString(){
        return  "photo : " +
                this.getContent() + "\t| ";
    }
}