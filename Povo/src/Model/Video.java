/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hafizhme
 */
public class Video extends Media{

    
    public Video(String path) {
        super(path);
    }
    public Video(int id, String path) {
        super(id, path);
    }
    
    @Override
    public void fileToContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
