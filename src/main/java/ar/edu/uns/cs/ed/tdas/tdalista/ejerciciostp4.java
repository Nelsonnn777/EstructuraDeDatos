package ar.edu.uns.cs.ed.tdas.tdalista;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.tdalista.IteradorPL;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdacola.ColaConArreglo;
import ar.edu.uns.cs.ed.tdas.tdalista.Dnodo;
import ar.edu.uns.cs.ed.tdas.tdalista.TdaLista;
import ar.edu.uns.cs.ed.tdas.tdapila.PilaConArreglo;
public class ejerciciostp4 {
    
    public static <E> Iterable<E> ej5(TdaLista<E> l1, TdaLista<E> l2){
        Iterable<Position<E>> pos = l2.positions();
        IteradorPL<E> l1pos = (IteradorPL<E>)l1.iterator();
        TdaLista<E> liseliminados = new TdaLista<E>();
        TdaLista<Position<E>> paraeliminar= new TdaLista<>();
        while(l1pos.hasNext()){
            E el = l1pos.next();
            for(Position<E> p : pos){
                if(p.element().equals(el)){
                    liseliminados.addLast(el);;
                    paraeliminar.addLast(p);
                    // Break; si hay q eliminar solo el primero 
                }

            }

        }
        for(Position<E> l : paraeliminar){
            l2.remove(l);
        }
        return liseliminados;
    }
    @SuppressWarnings("unchecked") 
    public static <E> TdaLista<E> ej6a(TdaLista<E> l1, TdaLista<E> l2){
        Iterable<Position<E>> posm;
        Iterator<E> it ;
        TdaLista<E> lisret = new TdaLista<>();
        if(l1.size()> l2.size()){
            posm = l1.positions();
            it = l2.iterator();
        }
        else {
            posm = l2.positions();
            it = l1.iterator();
        };

        for (Position<E> p : posm){
            lisret.addLast(p.element());
            if(it.hasNext()){
                lisret.addLast(it.next());
            }
        }
        return lisret;
    }

    public static <E> TdaLista<Integer> ej6b(TdaLista<Integer> l1, TdaLista<Integer> l2){
        TdaLista<Integer> lisret = new TdaLista<Integer>();
        Position<Integer> cursor1 = l1.first();
        Position<Integer> cursor2 = l2.first();
        Position<Integer> col1 = l1.next(l1.last());
        Position<Integer> col2 = l2.next(l2.last());
        while(cursor1 != col1 || cursor2!= col2){
            Integer c1 = cursor1.element();
            Integer c2 = cursor2.element();
            if( c2 == null || (c1 != null && c1 < c2)){ // ARREGLAR ESTO
                lisret.addLast(c1);
                cursor1 = l1.next(cursor1);
            }
            else if (c1 == null ||(c2 != null && c1 > c2)){ // Y ESTO
                lisret.addLast(c2);
                cursor2 = l2.next(cursor2);
            } else if(c1 == c2){
                lisret.addLast(c1);
                cursor1 = l1.next(cursor1);
                cursor2 = l2.next(cursor2);
            }

        }
        return lisret;
        
    }
     public static<E> void ej7(TdaLista<E> l1, TdaLista<E> l2){
        Iterable<Position<E>> pos = l1.positions();
        IteradorPL<E> l2pos = (IteradorPL<E>)l2.iterator();
        TdaLista<Position<E>> paraeliminar= new TdaLista<Position<E>>();
        while(l2pos.hasNext()){
            E el = l2pos.next();
            for(Position<E> p : pos){
                if(p.element().equals(el)){
                    paraeliminar.addLast(p);
                    // Break; si hay q eliminar solo el primero 
                }

            }

        }
        for(Position<E> l : paraeliminar){
            l1.remove(l);
        }
        PilaConArreglo<E> paux = new PilaConArreglo<E>();
        l2pos = new IteradorPL<E>(l2);
        while (l2pos.hasNext()) {
            paux.push(l2pos.next());
        }
        while(paux.size()!=0){
            l1.addLast(paux.pop());
        }

    }
} 
    

