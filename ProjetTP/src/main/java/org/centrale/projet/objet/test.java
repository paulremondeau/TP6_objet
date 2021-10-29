/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Scanner;

/**
 *
 * @author bodet
 */
public class test {
    
    public static int fibo(int n) {
        int res;
        int fibo1=1;
        int fibo2=1;
        
        res=1;
        if(n<=0) {res=0;}
        if(n==0 || n==1) {res=1;}
        for(int i=3;i<=n;i++) {
            res=fibo1+fibo2;
            fibo1=fibo2;
            fibo2=res;
        }
        return res;
    }
    
    public static void main(String[] args){
        System.out.println("Entrez un nombre : ");
        Scanner sc = new Scanner(System.in);
        int fiboNb = sc.nextInt();
        int resultat;
        resultat = fibo(fiboNb);
        System.out.println("Fibo("+fiboNb+") =  "+resultat);
    }
    
}
