/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Database;
import Model.Aplikasi;
import View.ViewUploudMedia2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 *
 * @author ThareeqAD
 */
public class Controlerviewmedia implements ActionListener{
     private Database connection;
     private ViewUploudMedia2 view;
     private Aplikasi aplikasi;
     private int idmed;

    public Controlerviewmedia(Aplikasi aplikasi,int id) {
        this.aplikasi = aplikasi;
        this.view = new ViewUploudMedia2();
        view = new ViewUploudMedia2();
        view.setVisible(true);
        view.addListener(this);
        idmed = id;
        if (idmed == -1){
            view.getBtnTag().setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
     Object o = ae.getSource();
     
     if(o.equals(view.getBtnTag())){
         String g = view.getTag().getText();
         aplikasi.tagPerson(idmed, aplikasi.getAccount(g));
         //int j = Integer.parseInt(view.getTag().getText());
//         int j = aplikasi.getAccount(view.getTag().getText());
//         aplikasi.tagPerson(0, j);

         //view.getTag().getText();
     }
    }
    
   
    
}
