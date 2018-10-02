package com.company.heap;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //création du tableau

        SortedHeap H = new SortedHeap(13);
        for(int i=0;i<10;i++){
            H.insertElement(i);
        }
        //System.out.println(H.element());
    }
}