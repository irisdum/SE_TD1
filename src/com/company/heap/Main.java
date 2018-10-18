package com.company.heap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //création du tableau
        myComparator mycomp = new myComparator();
        SortedHeap H = new SortedHeap(13, mycomp);
        for(int i=0;i<10;i++){
         int nbreAleat = (int) (Math.random() * (100 + 1));
         System.out.println(nbreAleat);
         H.insertElement(nbreAleat);
         }
        System.out.println("La LISTE" +H.Prioritaire());
        //Test des différentes fonctions
        /**for(int l=0;l<10;l++) {
            //System.out.println("Le plus grand element est " + H.element());
            System.out.println("Le plus grand element est " + H.popElement());}
            //System.out.println("Le plus grand element est " + H.pop2element());
            //System.out.println("La LISTE" +H.Prioritaire());
        }*/
    }
}

