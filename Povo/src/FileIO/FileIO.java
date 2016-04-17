/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileIO;

import Model.Admin;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hafizhme
 */
public class FileIO {
    public void saveFile(Admin admin) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.dat"));
        oos.writeObject(admin);
        oos.close();
    }
    public Admin loadFile() throws ClassNotFoundException, IOException{
        Admin admin = null;
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream("file.dat"));
            admin =  (Admin) ois.readObject();
            ois.close();
        } catch (IOException ex) {
            saveFile(admin);
        }
        return admin;
    }
}
