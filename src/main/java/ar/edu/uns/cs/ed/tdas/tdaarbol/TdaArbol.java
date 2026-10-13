package ar.edu.uns.cs.ed.tdas.tdaarbol;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

public class TdaArbol<E> implements Tree<E> {
    private TNodo<E> raiz;
    private int cantidad;

    public TdaArbol(){
        raiz = null;
        cantidad = 0;
    }
    
    public int size(){
        return cantidad;
    }

    public boolean isEmpty(){
        return cantidad == 0;
    }

    public void createRoot(E e){
        raiz = new TNodo<E>(e, null);
    }

    public Position<E> root(){
        if(raiz != null){
            throw new EmptyTreeException("Abrol vacio");
        }
        return raiz;
    }
    protected TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
        if(p== null){throw new InvalidPositionException("posicion nula");}
        TNodo<E> nodo;
        try{nodo =(TNodo<E>) p;}
            catch(ClassCastException e){
                throw new InvalidPositionException("NO es un nodo");
            }
        
        return nodo;
    }
    
    public Position<E> parent(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        TNodo<E> nodo = checkPosition(p);
        if(nodo == raiz){throw new BoundaryViolationException("es la raiz");}
        return nodo.getPadre();
    }

    public Iterable<Position<E>> children(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        Iterable<Position<E>> hijos = nodo.hijos();
        return hijos; 
    }

    public E replace(Position<E> p, E e) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        nodo.setElement(e);
        return e;
    }

    public boolean isInternal(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        Iterator<Position<E>> hh = nodo.hijos().iterator();
        return hh.hasNext();
    }

    public boolean isExternal(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        Iterator<Position<E>> hh = nodo.hijos().iterator();
        return !hh.hasNext();
    }

    public boolean isRoot(Position<E> p) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        return nodo == raiz;
    } 

    public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        TNodo<E> nuevo = new TNodo<E>(e, nodo);
        PositionList<Position<E>> hijos = nodo.hijos();
        hijos.addFirst(nuevo);
        cantidad++;
        return nuevo;
    }

    public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException{
        TNodo<E> nodo = checkPosition(p);
        TNodo<E> nuevo = new TNodo<E>(e, nodo);
        PositionList<Position<E>> hijos = nodo.hijos();
        hijos.addLast(nuevo);
        cantidad++;
        return nuevo;
    }
    

    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        TNodo<E> nodo = checkPosition(p);
        Position<Position<E>> pos = null;
        if(parent(rb) != p){throw new InvalidPositionException("las posiciones no son padre e hijo respectivamente");}
        for (Position<Position<E>> hijo : nodo.hijos().positions()) {
            if(hijo.element() == rb){
                pos = hijo;
                break;
            }
        }
        TNodo<E> nuevo = new TNodo<E>(e, nodo);
        nodo.hijos().addBefore(pos, nuevo);
        cantidad++;
        return nuevo;
    }

    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
          TNodo<E> nodo = checkPosition(p);
        Position<Position<E>> pos = null;
        if(parent(lb) != p){throw new InvalidPositionException("Las posiciones no son padre e hijo respectivamente");}
        for (Position<Position<E>> hijo : nodo.hijos().positions()) {
            if(hijo.element() == lb){
                pos = hijo;
                break;
            }
        }
        TNodo<E> nuevo = new TNodo<E>(e, nodo);
        nodo.hijos().addAfter(pos, nuevo);
        cantidad++;
        return nuevo;
    }

    
    public void removeExternalNode(Position<E> p) {
        if(isEmpty()){throw new InvalidPositionException("Arbol vacio");}
        TNodo<E> nodo = checkPosition(p);
        if(!nodo.hijos().isEmpty()){ throw new InvalidPositionException("No es un nodo externo");}
        TNodo<E> padre= checkPosition(nodo.getPadre());
        for(Position<Position<E>> hijos : padre.hijos().positions()){
            if((hijos.element() == p)){
                padre.hijos().remove(hijos);
                break;
            }
        }
        cantidad--;
    }   

    @Override
    public void removeInternalNode(Position<E> p) {
        if(cantidad== 0){throw new InvalidPositionException("Arbol vacio");}
        TNodo<E> nodo = checkPosition(p);
        PositionList<Position<E>> hijos= nodo.hijos();
        if(nodo == raiz){ // Si hay q eliminar la raiz
            if(hijos.size() != 1){throw new InvalidPositionException("Es raiz con mas de un hijo");}
            raiz = checkPosition(hijos.first().element());
            raiz.setPadre(null);
            cantidad--;
        }
        else if(!hijos.isEmpty()){
            TNodo<E> padre = checkPosition(parent(p));
            PositionList<Position<E>> hermanos = padre.hijos();
            Position<Position<E>> posicion = null;
            for (Position<Position<E>> pos :hermanos.positions()){
                if (pos.element() == p){
                    posicion = pos;
                    break;
                }
            }
            Position<Position<E>> paraeliminar = posicion;
            for(Position<E> h : hijos){
                hermanos.addAfter(posicion, h);
                posicion = hermanos.next(posicion);
            }
            hermanos.remove(paraeliminar);
            cantidad--;
        }
        

    }

    public void removeNode(Position<E> p){
        TNodo<E> nodo = checkPosition(p);
        if(nodo.hijos().isEmpty()){
            removeExternalNode(p);
        }
        else{
            removeInternalNode(p);
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<Position<E>> it =raiz.hijos().iterator();
        return it;
    }
    @Override
    public Iterable<Position<E>> positions() {
        // TODO Auto-generated method stub
        return null;
    }



    




}
