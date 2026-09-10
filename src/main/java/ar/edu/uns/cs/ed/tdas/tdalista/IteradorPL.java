package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;

public class IteradorPL<E> implements Iterator<E> {
        
    protected PositionList<E> list;
    protected Position<E> cursor;

    public IteradorPL(PositionList<E> l){
        list = l;
        if(list.isEmpty()){
            cursor = null;
        }
        else{
            try{
                cursor = list.first();
            } catch (EmptyListException e){
                e.printStackTrace();
            } 
        }

    }

    public boolean hasNext(){
        return cursor !=null;
    }

    public E next() throws NoSuchElementException{
        if (cursor == null){
            throw new NoSuchElementException("No existe un siguiente elemento.");
        }
         E toReturn = cursor.element();
         try{
             cursor =(cursor== list.last()) ? null : list.next(cursor);
         } catch (InvalidPositionException | BoundaryViolationException| EmptyListException e){
            e.printStackTrace();
        }
        return toReturn;
    }
    public boolean ej3a(E e1){
        boolean esta = false;
        if(cursor!= null){
            while(!esta && n!=)
        }
    }



}
