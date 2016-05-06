/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.Account;
import Model.Media;
import Model.Photo;
import Model.Video;
import View.ViewAwal;
import com.sun.javafx.tk.Toolkit;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hafizhme
 */
public class Database {
    private String server = "jdbc:mysql://localhost:3306/povo", dbuser = "root", dbpass = "";
    private String query = null;
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;
    
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/povo", "root", "");
        } catch (SQLException ex) {
            System.out.println(ex);;
        }        
    }
    
    public ResultSet getData(String SQLString) {
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public void query(String SQLQuery) throws SQLException {
        try {
            statement = connection.createStatement();
            System.out.println("menjalankan query : " + SQLQuery);
            statement.executeUpdate(SQLQuery);
            System.out.println("query berhasil dijalankan ");
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    public ResultSet queryWithGeneratedKey(String SQLQuery) throws SQLException {
        try {
            statement = connection.createStatement();
            System.out.println("menjalankan queryWithGeneratedKey : " + SQLQuery);
            statement.execute(SQLQuery,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            System.out.println("query berhasil dijalankan ");
            return resultSet;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    public List<Account> retrieveAllAccount() {
        System.out.println("retrieveAllAccount");
        List<Account> listAccount = new ArrayList<Account>();    
        query = "SELECT * FROM Account";
        
        try {
            resultSet = getData(query);
            while(resultSet.next()) {
                Account account = new Account(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("displayname"),
                    resultSet.getString("email")
                );
                System.out.println(account + "retrieved from table Account");
                listAccount.add(account);
            }
        } catch (SQLException ex) {
            System.out.println(ex);;
        }
        System.out.println("retrieveAllAccount SELESAI");
        return listAccount;
    }
    
    public void retrieveAllMedia(List<Account> listAccount) {
        System.out.println("retrieveAllMedia");
        for (Account account : listAccount) {
            query = "SELECT * FROM Media WHERE username='" + account.getUsername() +"'";
            try {
                resultSet = getData(query);
                while(resultSet.next()) {
                    String path = resultSet.getString("path");
                    Media media;
                    if ((path.contains(".jpg")) || (path.contains(".png"))) {
                        media = new Photo(
                                resultSet.getInt("id_media"),
                                path
                        );
                        String qwr = "SELECT username FROM Tag WHERE id_media=" + media.getId();
                        ResultSet rs = getData(qwr);
                        while (rs.next()) {                            
                            for (int i = 0; i < listAccount.size(); i++) {
                                Account calonTag = listAccount.get(i);
                                if (calonTag.getUsername().equals(rs.getString("username"))) {
                                    media.tagPerson(calonTag);
                                    break;
                                }
                            }
                        }
                    } else if ((path.contains(".mp4")) || path.contains(".avi")) {
                        media = new Video(
                                resultSet.getInt("id_media"),
                                path
                        );
                        String qwr = "SELECT username FROM Tag WHERE id_media=" + media.getId();
                        ResultSet rs = getData(qwr);
                        while (rs.next()) {                            
                            for (int i = 0; i < listAccount.size(); i++) {
                                Account calonTag = listAccount.get(i);
                                if (calonTag.getUsername().equals(rs.getString("username"))) {
                                    media.tagPerson(calonTag);
                                    break;
                                }
                            }
                        }
                        
                    }                                                        
                }
            } catch (SQLException ex) {
                System.out.println(ex);;
            }
            
        }
        System.out.println("retrieveAllMedia SELESAI");
        
    }

    public void retrieveAllFriend(List<Account> listAccount) {
        for (Account account : listAccount) {
            query = "SELECT following FROM Follow WHERE follower='" + account.getUsername() + "'";
            try {
                resultSet = getData(query);
                while(resultSet.next()) {
                    for (int i = 0; i < listAccount.size(); i++) {
                        Account calonTag = listAccount.get(i);
                        if (calonTag.getUsername().equals(resultSet.getString("following"))) {
                            account.followFriend(calonTag);
                            System.out.println("From " + account + ", " + calonTag + " Retrieved friendlist");
                            break;
                        }
                    }
                }
                
            } catch (SQLException ex) {
                System.out.println(ex);;
            }
            
        }
        System.out.println("retrieveAllMedia SELESAI");
    }
    
    public void saveAccount(Account account) {
        query = "INSERT INTO Account(username, password, displayname, email ) VALUES "
               + "('"+account.getUsername()+"','"+account.getPassword()+"','"+account.getDisplayname()+"','"+account.getEmail()+"')";
        try {
            statement.execute(query);
            System.out.println(account + "saved in table Account");
        } catch (SQLException ex) {
           
            System.out.println(query);
           
            ex.getStackTrace();
        }
        
    }
    
    
    public void saveMedia(Media media, Account account) {
        //+ "('"+account.getUsername()+"','"+account.getPassword()+"'
        query = "INSERT INTO media(content,username) VALUES "
                + "('"+ media.getPath()+"','"+ account.getUsername() + "')";
        
        try {
            resultSet = queryWithGeneratedKey(query);
            resultSet.next();
            media.setId(resultSet.getInt(1));
            System.out.println(media + " tersimpan di table Media");
        } catch (SQLException ex) {
            System.out.println(ex);;
        }       
    }
    
    
    public void saveFollow(Account follower, Account following) {
        query = "INSERT INTO Follow(follower,following) VALUES("
                + "'" + follower.getUsername() + "', "
                + "'" + following.getUsername() + "')";
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }
    
    public void saveTag(Media media, Account account) {
        query = "INSERT INTO Tag('id_media', 'username') VALUES("
                + media.getId() + ", "
                + "'" + account.getUsername() + "')";
        
        try {
            query(query);
            System.out.println("Tersimpan di table Tag ");
        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }
    
    public void removeTag(Account account, Media media) {
        query = "DELETE FROM Tag WHERE "
                + "id_media=" + media.getId()
                + " AND "
                + "username='" + account.getUsername();
        try {
            query(query);
            System.out.println("Tag " + account + " di " + media + " berhasil dihapus dari table tag");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void removeMedia(Media media) {
        query = "DELETE FROM Media WHERE "
                + "id_media=" + media.getId();
        try {
            query(query);
            System.out.println(media + " berhasil dihapus dari table Media");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void removeFriend(Account follower, Account following) {
        query = "DELETE FROM Follow WHERE "
                + "follower='" + follower.getUsername() + "'"
                + " AND "
                + "following='" + following.getUsername() + "'";
        try {
            query(query);
            System.out.println(following + " telah dihapus dari friend list " + follower + " dari tabel Follow");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
      public DefaultTableModel getData(){
       DefaultTableModel dm = new DefaultTableModel();
       dm.addColumn("username");
             String sql = "SELECT `username` FROM `account`";    
      try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/povo", "root", "");
           statement = connection.prepareStatement(sql);
           ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String username = rs.getString(1);              
                dm.addRow(new String[]{username});
            }
            return dm;
        } catch (SQLException ex) {
           ex.getStackTrace();
        }
        return null;
    }
     
      public boolean updatetable(String name){
        try {
            String sql = "update account set username ='"+name+"'";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/povo", "root", "");
            statement = connection.prepareStatement(sql);
            statement.execute(sql);
            return true;
        } catch (SQLException ex) {
           ex.getStackTrace();
        }
        return false;
      }

      
         public boolean deletetable(String name){
        try {
            String sql = "delete from  account where username ='"+name+"'";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/povo", "root", "");
            statement = connection.prepareStatement(sql);
            statement.execute(sql);
            return true;
        } catch (SQLException ex) {
           ex.getStackTrace();
        }
        return false;
      }
         
       public DefaultTableModel getimage(){
       DefaultTableModel dm = new DefaultTableModel();
       dm.addColumn("idmedia");
             String sql = "SELECT `id_media` FROM `media`";    
      try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/povo", "root", "");
           statement = connection.prepareStatement(sql);
           ResultSet rs = statement.executeQuery(sql);
           int column = rs.getMetaData().getColumnCount();
            while (rs.next()){
              Object [] rowData = new Object[column];
                for (int i = 0; i < rowData.length; ++i)
                {
                    rowData[i] = rs.getObject(i+1);
                }
                dm.addRow(rowData);         
            }
            return dm;
        } catch (SQLException ex) {
           ex.getStackTrace();
        }
        return null;
    }
       
     private void loadData() {
         DefaultTableModel dm = new DefaultTableModel();
         String sql = "select * from media";
         try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/povo", "root", "");
           statement = connection.prepareStatement(sql);
           ResultSet rs = statement.executeQuery(sql);
           ResultSetMetaData metaData = rs.getMetaData();

            // Names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            // Data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }
            dm.setDataVector(data, columnNames);
        } catch (Exception e) {
            e.getStackTrace();
    }

}
}
