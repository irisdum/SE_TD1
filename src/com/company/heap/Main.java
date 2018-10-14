package com.company.heap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //création du tableau
        myComparator mycomp = new myComparator();
        SortedHeap H = new SortedHeap(13, mycomp);
        /**for(int i=0;i<9;i++){
         int nbreAleat = 0 + (int)(Math.random() * (15 + 1));
         System.out.println(nbreAleat);
         H.insertElement(nbreAleat);
         }*/
        //Test des différentes fonctions
        //System.out.println("Le plus grand element est " + H.element());
        //System.out.println("Le plus grand element est " + H.popElement());
        //System.out.println("Le plus grand element est " + H.popElement());
        //Exercice 4 : on cherche à trier un tas par ordre de priorité. Dans notre exemple du plus grand au plus petit

        SortedHeap Tas = new SortedHeap(20, mycomp); //le tas à trier
        for(int i=0;i<9;i++){
            int nbreAleat = 0 + (int)(Math.random() * (100 + 1));
            System.out.println(nbreAleat);
            Tas.insertElement(nbreAleat);
         }
         System.out.println(Tas.Prioritaire());


    }
}