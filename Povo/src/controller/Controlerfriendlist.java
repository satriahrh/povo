/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Database;
import Model.Account;
import Model.Aplikasi;
import Model.Media;
import View.ViewUploudMedia2;
import View.friendList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableColumnModelEvent;

/**
 *
 * @author ThareeqAD
 */
public class Controlerfriendlist extends MouseAdapter implements ActionListener{
    private Database connection;
    private friendList view;
    private Aplikasi aplikasi;
    private List<Account> listAccount;
    private Statement q;
    private ResultSet s;
    private int id;
//    private Media media;
    private Account a;
    
    public Controlerfriendlist(Aplikasi aplikasi,Account a) {
        connection = new Database();
        this.aplikasi = aplikasi;
        view = new friendList();
        view.setVisible(true);
        view.addListener(this);
        view.setjTable1(aplikasi.getUsername());
        view.mouseadapter(this);
        this.a = a;
//        JScrollPane spanel = view.getjScrollPane1();
//        CostumTable(spanel);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
     Object o = ae.getSource();   
     if(o.equals(view.getBtnPilih())){
         System.out.println("a");
         view.getjPanel2();
         connection.getData();
         
         //connection.getimage();
         new Controlerviewacount(aplikasi,a,aplikasi.getAccoount(id));
         view.dispose();
     }
     else if (o.equals(view.getRefresh())){
          aplikasi.getAccoount(0);
     //     view.setListIdEmployee(aplikasi.getListEmployee());
     }
    
    }

//    private void CostumTable(JScrollPane spanel) {
//        String[] columnNames = {"First Name",
//                        "Last Name",
//                        "Sport",
//                        "# of Years",
//                        "Vegetarian"};
//        Object[][] data = {
//    {"Kathy", "Smith",
//     "Snowboarding", new Integer(5), new Boolean(false)},
//    {"John", "Doe",
//     "Rowing", new Integer(3), new Boolean(true)},
//    {"Sue", "Black",
//     "Knitting", new Integer(2), new Boolean(false)},
//    {"Jane", "White",
//     "Speed reading", new Integer(20), new Boolean(true)},
//    {"Joe", "Brown",
//     "Pool", new Integer(10), new Boolean(false)}
//    };
//        
//    JTable table = new JTable(data, columnNames);
//    spanel.add(table);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
    public void mousePressed(MouseEvent e){
        Object source = e.getSource();
        if (source.equals(view.getTableUser())){
            id = view.getSelectedID();
            Account ac = aplikasi.getAccoount(id);
            view.setTextField(ac.getUsername());
        }
    }

}
