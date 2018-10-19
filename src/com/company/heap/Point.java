package com.company.heap;

public class Point {
    int x;
    int y;
    static int ref=0;
    public Point(int X,int Y){
        this.x=X;
        this.y=Y;
        ref=ref+1; //Leur donner tous un nom différent
    }

    /**
     *
     * @param P point par arrport auquel on calcule la distance
     * @return
     */
    public Integer distance(Point P){
        double calc= Math.pow(this.x-P.x,2)+Math.pow(this.y-P.y,2);
        Integer dist= (int) Math.round(calc);
        return dist;
    }
}
