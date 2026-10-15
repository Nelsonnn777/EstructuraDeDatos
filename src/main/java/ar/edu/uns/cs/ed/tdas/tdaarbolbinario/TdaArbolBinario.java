package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdalista.TdaLista;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.tdamapeo.HashMapeo;
public class TdaArbolBinario<E> implements BinaryTree<E>{
    private BTNodo<E> raiz;
    private int cantidad;

    public boolean isEmpty(){
        return cantidad == 0;
    }

    public int size(){
        return cantidad;
    }

    private BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
        if(p == null){throw new InvalidPositionException("La posicion es nula");}
        BTNodo<E> a;
        try {
           a =  (BTNodo<E>)p;
        } catch (ClassCastException e) { throw new InvalidPositionException("La posicion no es valida");}
        return a;
    }
    
    public Position<E> left(Position<E> p){
        BTNodo<E> padre = checkPosition(p);
        if(padre.getHijoI() == null){throw new BoundaryViolationException("La Posicion no tiene hijo izquierdo");}
        return padre.getHijoI();
    }   
    public Position<E> right(Position<E> p){
        BTNodo<E> padre = checkPosition(p);
        if(padre.getHijoD() == null){throw new BoundaryViolationException("La Posicion no tiene hijo derecho");}
        return padre.getHijoD();
    }   
    public boolean hasLeft(Position<E> p){
        BTNodo<E> padre = checkPosition(p);
        return padre.getHijoI()!= null;
    }
    public boolean hasRight(Position<E> p){
        BTNodo<E> padre = checkPosition(p);
        return padre.getHijoD() != null;
    }

    //ADDS

    public Position<E> addLeft(Position<E> p, E e){
        BTNodo<E> padre = checkPosition(p);
        if(isEmpty()){throw new InvalidPositionException("Arbol vacio!!");}
        if(padre.getHijoI() != null){throw new InvalidPositionException("La posicion ya cuenta con un hijo izquierdo");}
        BTNodo<E> añadir = new BTNodo<E>(e);
        añadir.setPadre(padre);
        padre.setHijoI(añadir);
        return añadir;
    }
    
    public Position<E> addRight(Position<E> p, E e){
        BTNodo<E> padre = checkPosition(p);
        if(isEmpty()){throw new InvalidPositionException("Arbol vacio!!");}
        if(padre.getHijoD() != null){throw new InvalidPositionException("La posicion ya cuenta con un hijo derecho");}
        BTNodo<E> añadir = new BTNodo<E>(e);
        añadir.setPadre(padre);
        padre.setHijoD(añadir);
        return añadir;
    }      
    // HECHO COMO LA MIERDA EL ATTACH
    public void attach(Position<E>p , BinaryTree<E> t1, BinaryTree<E> t2){
        if(isEmpty()){throw new InvalidPositionException("El arbol receptor esta vacio");}
        BTNodo<E> nodo = checkPosition(p);
        if(!pertenecePre(nodo)){throw new InvalidPositionException("Esta posicion no pertenece al arbol");}
        nodo.setHijoD(checkPosition(t2.root()));
        nodo.setHijoI(checkPosition(t1.root()));

    }
    private boolean pertenecePre(BTNodo<E> p){
        if(p == raiz){
            return true;
        }
        if(p.getPadre() != null){
            return pertenecePre(p.getHijoD()) || pertenecePre(p.getHijoI());
        }
        return false;
        
    }

    // extendidos de Tree
    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        BTNodo<E> nodo = checkPosition(v);
        PositionList<Position<E>> hijos = new TdaLista<Position<E>>();
        if(hasLeft(nodo)){hijos.addLast(nodo.getHijoI());}
        if(hasRight(nodo)){hijos.addLast(nodo.getHijoD());}
        return hijos;

    }
    @Override
    public Position<E> parent(Position<E> v) {
        BTNodo<E> nodo = checkPosition(v);
        return nodo.getPadre();
    }
    @Override
    public Position<E> root() {
        return raiz;
    }
    @Override
    public void createRoot(E e) {
        if(raiz != null){throw new InvalidOperationException("la raiz ya cuenta con una raiz");}
        BTNodo<E> nuevaraiz = new BTNodo<E>(e);
        raiz = nuevaraiz;
    }
    @Override
    public boolean isExternal(Position<E> v) {
        BTNodo<E> nodo = checkPosition(v);
        return nodo.getHijoD()== null && nodo.getHijoI() == null;
    }

    public boolean isInternal(Position<E> v){
        BTNodo<E> nodo = checkPosition(v);
        return nodo.getHijoD() != null || nodo.getHijoI() != null;
    }



    
    public Iterator<E> iterator() {
        PositionList<E> lista = new TdaLista<E>();
        for (Position<E> position : positions()) {
            lista.addLast(position.element());
        }
        return lista.iterator();
    } 
    
    public Iterable<Position<E>> positions() {
        // visita
        PositionList<Position<E>> pos = new TdaLista<Position<E>>();
        if(!isEmpty()){
        guardarPreOrden(pos, raiz);
    }
        return pos;
    }
    private void guardarPreOrden(PositionList<Position<E>> l, Position<E> p){
        //visita 
        l.addLast(p);
        BTNodo<E> nodo = checkPosition(p);
        if(hasLeft(p)){guardarPreOrden(l, nodo.getHijoI());}
        if(hasRight(p)){guardarPreOrden(l,nodo.getHijoD());}
        
    }
    @Override
    public E replace(Position<E> v, E e) {
        BTNodo<E> nodo = checkPosition(v);
        E elementoeliminado = nodo.element();
        nodo.setE(e);

        return elementoeliminado;

    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        return addLeft(p, e);
    }
    
    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        return addRight(p, e);
    }

    @Override
    public void removeExternalNode(Position<E> p) {
        BTNodo<E> nodo = checkPosition(p);
        if(isInternal(nodo)){throw new InvalidPositionException("no es un nodo externo");}
        BTNodo<E> padre = nodo.getPadre();
        if(padre.getHijoD()== nodo){ padre.setHijoD(null);
            cantidad--;
        }
        if(padre.getHijoI()== nodo){ padre.setHijoI(null);
            cantidad--;
        }

    }


    //CLAUSURADOS

    
    @Override
    public void removeInternalNode(Position<E> p) {
        BTNodo<E> nodo = checkPosition(p);
        if(raiz == nodo){
            boolean hl = hasLeft(raiz);
            boolean hr = hasRight(raiz);
            if(hl && hr){ throw new InvalidPositionException("La posicion es la raiz y cuenta con mas de un hijo");}
            if(hl && !hr){raiz.getHijoI().setPadre(null);
                raiz = raiz.getHijoI();
            }
            if(!hl && hr){raiz.getHijoD().setPadre(null);
                raiz = raiz.getHijoD();}
        
        }   
        else{
            if(isInternal(p)){
            BTNodo<E> padre = checkPosition(parent(nodo));
            boolean hl = hasLeft(nodo);
            boolean hr = hasRight(nodo);
            Iterable<Position<E>> pos = children(nodo);
            if(padre.getHijoD() == nodo){
                for(Position<E> i : pos){
                    BTNodo<E> pi = checkPosition(i);
                    padre.setHijoD(pi);
                    pi.setPadre(padre);
                    break;
                }
            }
            if(padre.getHijoI() == nodo){
                for(Position<E> i : pos){
                    BTNodo<E> pi = checkPosition(i);
                    padre.setHijoI(pi);
                    pi.setPadre(padre);
                    break;
                }
                
            }
            }

        }
    }
    @Override
    public boolean isRoot(Position<E> v) {
        return checkPosition(v) == raiz;
    }
}
