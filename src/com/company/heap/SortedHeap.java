package com.company.heap;

import com.company.heap.Heap;

public abstract class SortedHeap implements Heap<Integer> {
        protected int ind;
        // indice du dernier fils
        protected int size;
        protected Integer queue[];

    public SortedHeap (int cap){
        this.size=cap;
        this.ind=0;
        this.queue=new Integer[cap];
    }

    public boolean insertElement(Integer s) {
        this.ind+=1;
        this.queue[this.ind]=s;
        while(s>this.queue[this.ind/2]){


        return false;
    }

    public boolean isEmpty() {
        return false;
    }

}
