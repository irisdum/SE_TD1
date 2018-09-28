package com.company.heap;

import com.company.heap.Heap;

import java.util.Comparator;
import java.util.Iterator;

public  abstract class SortedHeap implements Heap<Integer> {
        protected int ind;
        // indice du dernier fils
        protected int size;
        protected Integer queue[];

    public SortedHeap (int cap){
        this.size=cap;
        this.ind=0; //Dernier indice parcourue
        this.queue=new Integer[cap];
    }
    public Iterator<Integer> iterator(){
        return new Itr();
    }


    protected class Itr implements Iterator<Integer> {
        protected Integer it;
        Itr() { //construction du constructeur
            this.it=ind; // comment initialiser le compteur ?
        }
            public boolean hasNext () {
                if (this.it>size) {
                    return false;
                } else{
                    return true;
                }
            }
            public Integer next () {
                this.it=this.it+1;
                return queue[this.it];
            }
            /**public void remove(){
            c'est optionnel

            }*/
    }

    public boolean insertElement(Integer s) {
        this.ind += 1;
        Itr curs = new Itr(); // crée un curseur locale qui nous permttra de remonter dans l'arbre
        this.queue[curs.it] = s;
        int n; //indice du père
        boolean B = false;
        while (B) { //compare avec le père
            // Si l'indice du fils est impair
            if (curs.it % 2 == 0) {
                n = curs.it / 2;
            } else {
                n = (curs.it + 1) / 2;
            }
            if (s > this.queue[n] && curs.it > 0) {
                this.queue[curs.it] = this.queue[n]; // échange père fils
                this.queue[n] = s;
                curs.it = n;
            } else {
                B = true;
            }

        }
        return true;
    }
    /**Integer element(){
        return this.queue[this.iterator()];

    }*/

    Integer popelement(){
        return new Integer(3);
    }
    public boolean isEmpty() {
        return false;
    }
}
//Il reste à compléter les fonctions element,popelement et boolean isEmpty()