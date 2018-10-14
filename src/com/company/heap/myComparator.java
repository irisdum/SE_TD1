package com.company.heap;

import java.util.Comparator;

public class myComparator implements Comparator<Integer> {

    /**
     * Compare deux Integer
     * @param n1
     * @param n2
     * @return n1-n2;
     */
    public int compare(Integer n1, Integer n2) {
        return n1 - n2;
    }

    /**
     * Test si n1 est plus grand ou égal à n2
     * @param n1
     * @param n2
     * @return vrai si n1>n2
     * @return faux si n2>n1
     */
    public boolean higher(Integer n1,Integer n2){
        return n1 - n2 >= 0;
    }
}
