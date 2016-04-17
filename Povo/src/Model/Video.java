/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Cha Aska
 */
public class Video extends Media{
   private String content;
   
    public Video(String content){
       this.content = content;
   }

   public String getContent(){
       return content;
   }
   public String toString(){
        return  "photo : " +
                this.getContent() + "\t| ";
    }
}
