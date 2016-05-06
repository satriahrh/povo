package controller;


import Database.Database;
import Model.Account;
import Model.Aplikasi;
import View.ViewSignUp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThareeqAD
 */
public class Controllersignup  implements ActionListener{
    private Database conection;
    private Aplikasi aplikasi;
    private ViewSignUp view;

    public Controllersignup(Aplikasi aplikasi) {
        this.aplikasi = aplikasi;
        this.view = new ViewSignUp();
        view = new ViewSignUp();
        view.setVisible(true);
        view.addListener(this);
       
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
       Object o = ae.getSource();
       
       if(o.equals(view.getBtnSign())){
           String username = view.getTxtUsername().getText();
           String password = view.getTxtPassword().getText();
           String Displayname = view.getTxtDisplayname().getText();
           String email = view.getEmail().getText();
           aplikasi.signUp(username, password, Displayname, email);
           JOptionPane.showMessageDialog(view, "Berhasil Buat akkun");
           new Controlerviewawal(aplikasi);
           view.dispose();
     }    
    }
}
