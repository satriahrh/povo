/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author hafizhme
 */
public class Photo extends Media {

    private BufferedImage content;

    public Photo(String path) {
        super(path);
    }
    
    public Photo(int id, String path) {
        super(id,path);
    }
    
    @Override
    public void fileToContent() {
        try {
            this.content = ImageIO.read(new FileInputStream(path));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public BufferedImage getContent(){ 
        return this.content;
    }
    
}
