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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hafizhme
 */
public class database {
    Connection connection;
    Statement statement;
        
    public void connect(){
        try {
            connection = DriverManager.getConnection("127.0.0.1","root","");
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}    

    public void disconnect(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean manipulate(String q) {
        int x = -1;
        try {

            x = statement.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (x >= 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public ResultSet getdata(String q){   
        try {
            return statement.executeQuery(q);
                    
                    } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }
}
