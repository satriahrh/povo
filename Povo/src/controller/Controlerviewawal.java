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
import View.ViewSignUp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ThareeqAD
 */
public class Controlerviewawal implements ActionListener{
    private Aplikasi aplikasi;
    private ViewAwal view;
    private ViewAccount view1;
    private Database connection;
    private Media media;

    public Controlerviewawal(Aplikasi aplikasi) {
        this.aplikasi = aplikasi;
        this.view = new ViewAwal();
        view = new ViewAwal();
        view1 = new ViewAccount();
        view.setVisible(true);
        view.addlistener(this);
          }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        
        if(o.equals(view.getBtnSignIn())){
            String username = view.getTxtUsername().getText();
            String password = view.getTxtPassword().getText();
            System.out.println("Click");
            
            if (aplikasi.signIn(username, password)!=null){
                new Controlerviewacount(aplikasi,aplikasi.signIn(username, password),null);
                
                view.dispose();   
            }
        }
        
            else if (o.equals(view.getBtnSignUp())){
                    new Controllersignup(aplikasi);
                    view.dispose();
                    }

    }
    
    
    
}
