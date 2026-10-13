package ar.edu.uns.cs.ed.tdas.tdamapeo;

import ar.edu.uns.cs.ed.tdas.tdamapeo.HashMapeo;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Entrada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdalista.TdaLista;

public class ej1tp5 {
    public static PositionList<Entry<Integer, Integer>> a(HashMapeo<Integer,Integer> m1, HashMapeo<Integer, Integer> m2){
        Iterable<Entry<Integer,Integer>> entradas1 = m1.entries();
        PositionList<Entry<Integer,Integer>> toret = new TdaLista();
        for (Entry<Integer,Integer> entry : entradas1) {
            Integer entry2 = m2.get(entry.getKey());
            if(entry.getValue() != entry2){
                toret.addLast(entry);
                Entry<Integer, Integer> e = new Entrada<Integer,Integer>(entry.getKey(), entry2);
                toret.addLast(e);
            }
        
        }
        return toret;

    }       

}