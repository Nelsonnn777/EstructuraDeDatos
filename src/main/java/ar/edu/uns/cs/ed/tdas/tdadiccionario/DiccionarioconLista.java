package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import ar.edu.uns.cs.ed.tdas.tdalista.TdaLista;
import ar.edu.uns.cs.ed.tdas.*;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.excepciones.*;
import ar.edu.uns.cs.ed.tdas.Entrada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

import java.util.Iterator;

import javax.management.openmbean.InvalidKeyException;
 

public class DiccionarioconLista<K,V> implements Dictionary<K, V> {
        PositionList<Entry<K,V>> diccionario;

        public DiccionarioconLista(){
            diccionario = new TdaLista<Entry<K,V>>();
        }
        public int size(){
            return this.diccionario.size();
        }
        public boolean isEmpty(){
            return 0 == size();
        }
        @Override 
        public Entry<K,V> find(K key){
            Iterator<Entry<K, V>> it = diccionario.iterator();
            Entry<K, V> toret = null;
            while(it.hasNext() || toret != null){
                Entry<K, V> actual = it.next();
                if(actual.getKey().equals(key)){
                    toret = actual;
                }
                
            }
            return toret;
        }
        public Iterable<Entry<K,V>> findAll(K key){
            Iterator<Entry<K,V>> it = diccionario.iterator();
            PositionList<Entry<K,V>> toret = new TdaLista<Entry<K,V>>();
            while(it.hasNext()){
                Entry<K,V> actual = it.next();
                if(actual.getKey().equals(key)){
                    toret.addLast(actual);
                }
            }

            return toret;

        }
        public Entry<K, V> insert(K key, V value){
            Entry<K,V> entrada = new Entrada<K,V>(key,value);
            diccionario.addLast(entrada);
            return entrada;
        }
        public Entry<K,V> remove(K key, V value){
               Iterable<Position<Entry<K, V>>> pos = diccionario.positions();
               Entry<K, V> toret = null;
               boolean enc = false;
               for(Position<Entry<K, V>> p : pos){
                if(p.element().getKey().equals(key) && p.element().getValue().equals(value)){
                    toret = p.element();
                    enc = true;
                    break;
                }
               }
               if(enc != true){
                throw new InvalidEntryException("no existe esta entrada");
               }
               return toret;
                
            }
 }




