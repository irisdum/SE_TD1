package com.company.heap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //création du tableau
        myComparator mycomp = new myComparator();
        SortedHeap H = new SortedHeap(13, mycomp);
        for (int i = 0; i < 10; i++) {
            int nbreAleat = (int) (Math.random() * (100 + 1));
            //System.out.println(nbreAleat);
            H.insertElement(nbreAleat);
        }
        System.out.println("La LISTE" + H.prioritaire());

        //La distance point en d2D
        ArrayList<Point> Points= new <Point> ArrayList(10);
        SortedHeap Distance = new SortedHeap(11,mycomp);
        for (int i = 0; i < 10; i++) {
            int nbx = (int) (Math.random() * (100 + 1));
            int nby = (int) (Math.random() * (100 + 1));
            Points.add(new Point(nbx,nby));
        }
        for (int i=1;i<10;i++){
            Distance.insertElement(Points.get(0).distance(Points.get(i)));
            System.out.println(Points.get(0).distance(Points.get(i)));
        }
        System.out.println("La liste des priorité " + Distance.prioritaire());
    }
}

