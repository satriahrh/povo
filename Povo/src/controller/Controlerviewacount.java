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
import View.ViewAccount;
import View.ViewAwal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ThareeqAD
 */
public class Controlerviewacount implements ActionListener{
    private Database connection;
    private Aplikasi aplikasi;
    private ViewAccount view;
    private Account a,b;
    
    public Controlerviewacount(Aplikasi aplikasi,Account a,Account b) {
        this.aplikasi = aplikasi;
        view = new ViewAccount();
        this.view = new ViewAccount();
        view.setVisible(true);
        view.addListener(this);
        view.setTeks(a);
//        view.setimage(media);
        if (b==null)
            view.setjLabel4(a);
        else
            view.setjLabel4(b);
        this.a = a;
        this.b = b;
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        
        if (o.equals(view.getBtnSignOut())){
            new Controlerviewawal(aplikasi);
            view.dispose();
//        }
//        else if (o.equals(view.getBtnMedia())) {
//            new Controlerviewmedia(aplikasi,-1);
//            view.dispose();
        } else if (o.equals(view.getBtnFriends())) {
            new Controlerfriendlist(aplikasi,a);
            view.dispose();
        }
        else if(o.equals(view.getUpload())){
            new Controlerupload(aplikasi,a);
            view.dispose();
        }
        else if(o.equals(view.getBtnSignOut())){
            aplikasi.signOut();
            new Controlerviewawal(aplikasi);
            view.dispose();
        }
        else if(o.equals(view.getBtnFollowFriends())){
            aplikasi.followFriend(b);
            JOptionPane.showMessageDialog(view, "berhasil menambah");
            
        }   
    }
    

    
}
