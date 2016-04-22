/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.Account;
import Model.Card;
import Model.Comment;
import Model.Media;
import static com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler.BUFFER_SIZE;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author hafizhme
 */
public class Database {
    private String server = "jdbc:mysql://localhost:3306/modul9", dbuser = "root", dbpass = "";
    private String query = null;
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;
    
    public void connect() {
        try {
            connection = DriverManager.getConnection(server, dbuser, dbuser);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void saveAccount(Account account) {
        query = "INSERT INTO Account('username','password','displayname','email') VALUES("
                + "'" + account.getUsername() + "', "
                + "'" + account.getPassword() + "', "
                + "'" + account.getDisplayname() + "', "
                + "'" + account.getEmail() + "')";
          
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveMedia(Media media, Account account) {
        
        query = "INSERT INTO Media('content','username') VALUES("
                + "'" + media.getContent() + "', "
                + "'" + account.getUsername() + "')";
        
        try {
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            media.setId(resultSet.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    public void saveComment(Comment comment, Media media) {
        query = "INSERT INTO Comment('text','username','id_media') VALUES("
                + "'" + comment.getText() + "', " 
                + "'" + comment.getAccount().getUsername() + "', "
                + media.getId() + ")";
        
        try {
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            comment.setId(resultSet.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveCard(Card card) {
        query = "INSERT INTO Card('username','id_media') VALUES("
                + "'" + card.getAccount().getUsername() + "', "
                + card.getMedia().getId() + ")";
        
        try {
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            card.setId(resultSet.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveCardTl(Card card, Account account) {
        query = "INSERT INTO Card_Tl('username','id_card') VALUES("
                + "'" + account.getUsername() + "', "
                + "'" + card.getId() + "')";
        
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveFollow(Account follower, Account following) {
        query = "INSERT INTO Follow('follower','followoing') VALUES("
                + "'" + follower.getUsername() + "', "
                + "'" + following.getUsername() + "')";
        
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveTag(Media media, Account account) {
        query = "INSERT INTO Tag('id_media', 'username') VALUES("
                + media.getId() + ", "
                + "'" + account.getUsername() + "')";
        
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Account> loadAccount() {
        List<Account> list = new ArrayList<>();
        query = "SELECT * FROM Account";
        
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                Account account = new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                list.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    public Account getAccount(String username) {
        Account account = null;
        query = "SELECT * FROM Account";
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if(resultSet.next()) {
                account = new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)                        
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return account;
    }
    
    public Media getMedia(int idMedia) {
        Media media = null;
        query = "SELECT * FROM Media";
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob(2);
                InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream("cache");
 
                int bytesRead = -1;
                byte[] buffer = new byte[parseInt(BUFFER_SIZE)];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
 
                inputStream.close();
                outputStream.close();
                
                media = new Media(
                        resultSet.getInt(1),
                        ImageIO.read(new FileInputStream("cache"))
                );
        }
        
       
        }   catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return media;   
    }
        
    public Card getCard(int idCard) {
        Card card = null;
        query = "SELECT * FROM Card";
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                card = new Card(
                        resultSet.getInt(1),
                        getAccount(resultSet.getString(2)),
                        getMedia(resultSet.getInt(3))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return card;
    }
    
    public List<Card> loadTimeLine(Account account) {
        List<Card> card_list = new ArrayList<>();
        List<Integer> id_list = new ArrayList<>();
        query = "SELECT id_card FROM Card_Tl WHERE username='" + account.getUsername() +"'";
        
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                card_list.add(getCard(resultSet.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return card_list;
    }
    
    public List<Media> loadMedia(Account account) {
        List<Media> list = new ArrayList<>();
        query = "SELECT * FROM Media WHERE username='" + account.getUsername() + "'";
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                Blob blob = resultSet.getBlob(2);
                InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream("cache");
 
                int bytesRead = -1;
                byte[] buffer = new byte[parseInt(BUFFER_SIZE)];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
 
                inputStream.close();
                outputStream.close();
                
                list.add(new Media(
                        resultSet.getInt(1),
                        ImageIO.read(new FileInputStream("cache"))
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Account> loadFollower(Account account) {
        List<Account> list = new ArrayList<>();
        query = "SELECT follower FROM Follow WHERE following='" + account.getUsername() + "'";
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                list.add(this.getAccount(resultSet.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Account> loadFollowing(Account account) {
        List<Account> list = new ArrayList<>();
        query = "SELECT following FROM Follow WHERE follower='" + account.getUsername() + "'";
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                list.add(this.getAccount(resultSet.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Account> loadTag(Media media) {
        List<Account> tag = new ArrayList<>();
        query = "SELECT username FROM Tag WHERE id_media='" + media.getId() + "'";
        try {
            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                tag.add(this.getAccount(resultSet.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tag;
    }
}
