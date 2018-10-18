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
    private int indPere(Itr i){
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
    private Integer indFils(int p){
            if(hasSon(p)){
                if (2*(p+1)==this.ind) {//2p+1 correspond a l'indice du dernier fils du père d'indice p
                    return 2 * p+1;
                }
                if (! this.comp.higher(this.queue[2 * p + 2],this.queue[2 * p+1])) { // Le fils 2p+2 plus petit que 2p+1
                    return 2 * p+1;
                } else {
                    return 2 * p + 2;
                }
    }else throw new NoSuchElementException();
    }

    /**
     * Indique si le père d'indice p a un fils
     * @param p indice du père
     * @return faux si il n'a pas de fils
     */
    private boolean hasSon(int p){
        if(this.ind+1 <= 2 * p + 1){
            return false;
        }else return true;
    }

    /**
     * Retrieves (and remove) the highest element of this heap
     *
     * @return the highest element of this heap
     * @throws NoSuchElementException if this heap is empty
     */

    /**public Integer popElement() {
        boolean B = true;
        int m = 0;
        if (isEmpty()) throw new NoSuchElementException("Le tas est vide");
        else {
            while (hasSon(m)) {// la boucle doit tourner tant que le fils étudié a une descendance
                //this.queue[m] = this.queue[this.indFils(m)];
                //System.out.println("Je mets "+this.queue[m]+"a la place de "+this.queue[this.indFils(m)]);
                echange(m,this.indFils(m));
                //this.queue[this.indFils(m)]=null;
                m = this.indFils(m);

            }
            Integer max=new Integer(queue[this.ind]);
            queue[this.ind]=-1;
            this.ind=this.ind-1; //On réduit le nombre d'élément inséré dans la liste de 1
           // System.out.println("indice du fils a explorer"+m);
            //System.out.println("indice max "+this.ind);
            this.insertElement(0); //on va échanger ensuite pour le mettre à la place du fils qui a remonté
            //this.echange(this.ind-1,m);
            //this.ind = this.ind - 2;//Diminue le nombre de valeurs totale entrée dans l'arbre et enève l'effet du this.insert
            //return this.queue[this.ind+1];
            //System.out.println(int i for i in this.queue);
            return this.queue[0]; //On a mis l'element le plus grand à la place du premier espace vide
        }
    }*/
   public Integer popElement(){
       int m = 0;
       if (isEmpty()) throw new NoSuchElementException("Le tas est vide");
       else {
           while (hasSon(m)) {// la boucle doit tourner tant que le fils étudié a une descendance
               //this.queue[m] = this.queue[this.indFils(m)];
               //System.out.println("Je mets "+this.queue[m]+"a la place de "+this.queue[this.indFils(m)]);
               echange(m,this.indFils(m));
               //this.queue[this.indFils(m)]=null;
               m = this.indFils(m);

           }
           Integer max=new Integer(queue[m]);
           queue[m]=-1; // j'ai supposé que je ne rentrais que des entiers strictement positifs, mettre notre borne inf-1
           //this.ind=this.ind-1; //On réduit le nombre d'élément inséré dans la liste de 1
           // System.out.println("indice du fils a explorer"+m);
           //System.out.println("indice max "+this.ind);
           //this.insertElement(-2); //on va échanger ensuite pour le mettre à la place du fils qui a remonte
           return max; //On a mis l'element le plus grand à la place du premier espace vide
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
        int k=0; //On initialise le compteur à zero
        while(k<this.ind-1){
            System.out.println("indice"+k);
            Prio.add(this.popElement());

            k=k+1;
        }

        return Prio;
    }

    /**
     * Echanger deux elements de la liste
      * @param p1 indice de l'element 1 à echanger
     * @param p2 indice de l'element 2 à changer
     */
    private void echange(int p1,int p2){
        Integer A=this.queue[p1];
        Integer B= this.queue[p2];
        this.queue[p1]=B;
        this.queue[p2]=A;

    }
}
