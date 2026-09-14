package ar.edu.uns.cs.ed.tdas;
import ar.edu.uns.cs.ed.tdas.tdacola.ColaConArreglo;
import ar.edu.uns.cs.ed.tdas.tdalista.TdaLista;
import ar.edu.uns.cs.ed.tdas.tdapila.PilaConArreglo;

public class practicaparcial{

    public static <E> PilaConArreglo<E> asd(PilaConArreglo<E> p, ColaConArreglo<E> q){
        PilaConArreglo<E> Caux = new PilaConArreglo<>();
        while(p.size() != 0){
            Caux.push(p.pop());
        }
        while(q.size()!= 0){
            p.push(q.dequeue());
        }
        while (Caux.size()!= 0) {
            p.push(Caux.pop());
        }
        return p;

    }
    public static <E> TdaLista<E> dividirlista(TdaLista<E> l, Position<E> p){
        Position<E> paux = p;
        TdaLista<E> toret = new TdaLista<>();
        while(l.next(paux) != cola){
            l.remove(paux);
            toret.addLast(paux.element());
            paux = l.next(paux);

        }
        return toret;
    }
}