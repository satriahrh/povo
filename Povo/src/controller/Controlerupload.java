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
import View.UploudMedia1;
import View.ViewUploudMedia2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ThareeqAD
 */
public class Controlerupload implements ActionListener{
    private Database connection;
    private UploudMedia1 view;
    private Aplikasi aplikasi;
  private Account a;
  private Media media;
    
    public Controlerupload(Aplikasi aplikasi, Account a) {
        this.aplikasi = aplikasi;
        this.view = new UploudMedia1();
        view.setVisible(true);
        view.addListener(this);
       
        view.setjTable(aplikasi.getMedia());
      
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       Object o = ae.getSource(); 
       if(o.equals(view.getBtnUploud())){
           String tag = view.getjTextFieldIsiFile().getText();
           aplikasi.uploadMedia(tag);
           JOptionPane.showMessageDialog(view,"berhasil diupload");
       }
       else if (o.equals(view.getBtnSignOut())){
           new Controlerviewacount(aplikasi,a,null);
           view.dispose();
       }   
       else if (o.equals(view.getBtnTag())){
           new Controlerviewmedia(aplikasi, view.getSelectedID());
           view.dispose();
       }
       
    }
    
    

    
}
