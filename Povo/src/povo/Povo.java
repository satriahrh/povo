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

public class Povo {
    
    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("------------POVO-----------");
        System.out.println("--Photo and Video Sharing--");
        System.out.println();
        System.out.println("1. Sign In");
        System.out.println("2. Sign Up");
        
        byte choice = (byte) sc.nextInt();
        
        if (choice == 1) {
            // go to sign in
        } else if (choice ==2 ) {
            // go to sign up
        }
        
        
    }
    
}
