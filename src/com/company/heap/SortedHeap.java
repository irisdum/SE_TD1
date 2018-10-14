package com.company.heap;

import java.util.*;

/**
 * Classe d'abre binaire implémentant la classe Heap qui étend l'inteface Itearable.
 */

public class SortedHeap implements Heap<Integer>  {
        protected int ind;
        // indice du dernier fils
        protected int size;
        protected Integer queue[];
        public myComparator comp;

    /**
     * Constructeur de la classe SortedHeap
     * @param  cap capacité maximale de l'objet SortedHeap
     */
    public SortedHeap (int cap, myComparator comparator){
        this.size=cap;
        this.ind=0; //Dernier indice parcourue
        this.queue=new Integer[cap];
        this.comp=comparator;
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
            return this.it + 1 <= size;
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
                if (this.comp.compare(s,this.queue[n])>0 && curs.it >= 0) {//le cas où la valeur du fils est plus grand que le père
                    this.queue[curs.it] = this.queue[n]; // échange père fils
                    this.queue[n] = s;
                    //System.out.println("on a mis l'element "+s+" à l'endroit "+n);
                    //System.out.println("on a mis "+ queue[curs.it]+" à l'endroit "+ curs.it);
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
        if (! isEmpty()){
        return this.queue[0];
        }
        else{
            throw new NoSuchElementException();
        }
    }
    public Integer nbNiveau(int n){
        boolean B= true;
        int k=0;
        while(k<n){
            if(n>(Math.pow(2,k)) && n<Math.pow(2,k+1)){
                return k;
            }else k=k+1;
        }
        return null;
    }

    /**
     * Fonction qui determine l'indice du plus grand fils, si il n'a pas de fils retourne son propre indice
     * @param p correspon à l'indice du père
     * @return l'indice du plus grand des deux fils et si il n'a pas de fils, retourne l'indice du père
     */
    public Integer indFils(int p){
            if (p != 0) {
                if (2*(p+1)==this.ind) {//2p+2 correspond a l'indice du dernier fils du père d'indice p
                    return 2 * p+1;
                }
                //System.out.println("indice " + 2 * p);
                if (! this.comp.higher(this.queue[2 * p + 2],this.queue[2 * p+1])) { // Le fils 2p+2 plus petit que 2p+1
                    return 2 * p+1;
                } else {
                    return 2 * p + 2;
                }
            } else {//cas p=0
                if (this.comp.higher(this.queue[1],this.queue[2])) {
                    return 1;
                } else {
                    return 2;
                }
            }
    }

    /**
     * Indique si le père d'indice p a un fils
     * @param p indice du père
     * @return faux si il n'a pas de fils
     */
    public boolean hasSon(int p){
        return this.ind > 2 * p + 1;
    }

    /**
     * Retrieves (and remove) the highest element of this heap
     *
     * @return the highest element of this heap
     * @throws NoSuchElementException if this heap is empty
     */

    public Integer popElement() { //problème je n'arrive pas à faire de copie profonde
        boolean B = true;
        int m = 0;
        //ArrayList<Integer> val = new ArrayList<Integer>(1);
        //val.add(this.queue[0]);
        //ArrayList<Integer> queue2=new List<Integer>(this.queue);
        //int high=queue[0];//on va modifier l'arbre, on copie la valeur du plus haut
        //if(!this.isEmpty()){
        //System.out.println("je suis ne suis pas vide");
        if (isEmpty()) throw new NoSuchElementException();
        else {
            while (hasSon(m)) {// la boucle doit tourner tant que le fils étudié a une descendance
                this.queue[m] = this.queue[this.indFils(m)];
                m = this.indFils(m);
                //System.out.println(this.ind);
            }
            this.ind = this.ind - 1;//Diminue le nombre de valeurs totale entrée dans l'arbre
            return this.queue[0];
        }
    }

    /**
     * Indique si l'arbre est vide
     * @return un boléen vrai si l'arbre est vide
     */
    public boolean isEmpty() {
        if (this.ind>0){ // dès qu'un élément est ajouté this.ind est incrémenté de un
        return false;
    }else{
        return true;
        }
    }

    /**
     * On retourne la liste des elements dans l'ordre de priorite
     * @return l'element le plus prioritaire
     */
    public ArrayList<Integer> Prioritaire(){ //Ne marche pas, une certaine valeur est répétée de nombreuses fois
        ArrayList<Integer> Prio=new ArrayList<Integer>(this.size);
        Itr curs=new Itr();
        while(this.ind>1){
            System.out.println(this.ind);
            Prio.add(this.element());
            popElement();
        }

        return Prio;
    }
}
