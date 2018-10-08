package com.company.heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe d'abre binaire implémentant la classe Heap qui étend l'inteface Itearable.
 */

public class SortedHeap implements Heap<Integer> {
        protected int ind;
        // indice du dernier fils
        protected int size;
        protected Integer queue[];

    /**
     * Constructeur de la classe SortedHeap
     * @param  cap capacité maximale de l'objet SortedHeap
     */
    public SortedHeap (int cap){
        this.size=cap;
        this.ind=0; //Dernier indice parcourue
        this.queue=new Integer[cap];
    }

    /**
     * Méthode Iterator adaptée à SortedHeap
     * @return un itérateur adapté
     */
    public Iterator<Integer> iterator(){
        return new Itr();
    }

    /**
     * class d'itérateur adapté à SortedHeap
     */
    protected class Itr implements Iterator<Integer> {
        protected Integer it;

        /**
         * Constructeur de la classe Itr
         */

        Itr() { //construction du constructeur
            this.it=ind; // comment initialiser le compteur ?
        }

        /**
         * Methode hasNext
         * @return un bolléen : vraie si il existe un element suivant, faux sinon
         */
        public boolean hasNext () {
            if (this.it+1>size) {
                return false;
            } else{
                return true;
            }
        }

        /**
         * Methode next
         * @return l'élément suivant dans l'arbre binaire
         */
        public Integer next () {
                this.it=this.it+1;
                return queue[this.it];
            }
            /**public void remove(){
            c'est optionnel

            }*/
    }

    /**
     * Calcul la valeur de l'indice du père
     * @param i curseur
     * @return la valeur de l'indice du père
     */
    public int indPere(Itr i){
        int p=0;
        if (i.it % 2 == 0 &&i.it>0) { //si l'indice est pair
            p = i.it / 2-1;
        }if(i.it==1){
            p=0;
        }if(i.it%2==1&&i.it>1){// si l'indice est impair
            p= (i.it + 1)/2-1;
        }
        return p;

    }

    /**
     * Fonction qui insert un élément dans l'arbre
     * @param s correspond à l'élément qui doit être ajouté à l'arbre
     * @return vraie si l'élément a bien pu s'insérer
     */
    public boolean insertElement(Integer s) {
        //int n=0; //indice du père
        Itr curs = new Itr(); // crée un curseur locale qui nous permettra de parcourir l'arbre
        if(curs.hasNext()){
            this.queue[curs.it] = s;
            boolean B = true; // variable qui traduit si le père est plus petit que le fils.
            //System.out.println(B);
            while (B) { //compare avec le père
                int n=indPere(curs);
                if (s > this.queue[n] && curs.it >= 0) {//le cas où la valeur du fils est plus grand que le père
                    this.queue[curs.it] = this.queue[n]; // échange père fils
                    this.queue[n] = s;
                    System.out.println("on a mis l'element "+s+" à l'endroit "+n);
                    System.out.println("on a mis "+ queue[curs.it]+" à l'endroit "+ curs.it);
                    curs.it = n; //on affecte l'indice du père dans la valeur de l'iterateur
                }else {
                    B = false; //On sort de la boucle while
                }

            }
            this.ind += 1;// On incrémente la valeur de l'indice du dernier élément parcouru
            return true;
        }else{
            System.out.println("L'arbre est trop petit");
            return false;
        }
    }


    /**
     * Retrieves (without removing) the highest element of this heap
     *
     * @return the highest element of this heap
     * @throws NoSuchElementException if this heap is empty
     */
    public Integer element(){
        
        return 3;
   }

    public Integer popElement(){
        return  2;
    }
    public boolean isEmpty() {
        return false;
    }
}
//Il reste à compléter les fonctions element,popelement et boolean isEmpty()