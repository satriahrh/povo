/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package povo;

import java.util.Scanner;

/**
 *
 * @author hafizhme
 */
public class Console {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void signUp() {
        System.out.print("\tusername\t: ");
        String username = sc.next();
            System.out.print("\tdisplayname\t: ");
            String displayname = sc.nextLine();
            System.out.print("\temail\t\t: ");
            String email = sc.next();
            System.out.print("\tpassword\t: ");
            String password = sc.next();
    }
    
    public static void signIn() {
        
    }
    
}