package ar.edu.uns.cs.ed.tdas.tdadiccionario;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdalista.TdaLista;
import ar.edu.uns.cs.ed.tdas.Entrada;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;

public class TdaDiccionario<K,V> implements Dictionary<K,V>{
    private int cant;
    private int N; 
    private PositionList<Entrada<K,V>> buckets[];

    @SuppressWarnings("unchecked")
    public TdaDiccionario(){
        cant = 0;
        N = 10;
        buckets = (PositionList<Entrada<K,V>>[]) new TdaLista[N];;

        for (int i = 0; i < N ; i++) {
            buckets[i] = new TdaLista<>();
        }
    }
    public int size(){
        return cant;
    }
    public boolean isEmpty(){
        return cant==0;
    }

    private int reHash(K key){
        return key.hashCode()%N;
    }
    public Entry<K,V> find(K key){
        if(key == null) throw new InvalidKeyException("key invalida");
        Entry<K,V> toret = null;
        int hashkey = reHash(key);
        for (Entrada<K,V> entrada : buckets[hashkey]) {
            if(key.equals(entrada.getKey())){
                toret = entrada;
                break;
            }
        }
        return toret;
    }

    public PositionList<Entry<K,V>> findAll(K key){
        if(key == null){ throw new InvalidKeyException("llave invalida");}
        int hashkey = reHash(key);
        PositionList<Entry<K,V>> toret = new TdaLista<Entry<K,V>>();
        for (Entry<K,V> entrada : buckets[hashkey]) {
            if(key.equals(entrada.getKey())){
                toret.addLast(entrada);
            }
        }
        return toret;

    }

    public Entry<K,V> insert(K key, V value){
        if(key == null){ throw new InvalidKeyException("llave invalida");}
        Entrada<K,V> nuevo = new Entrada<K,V>(key,value);
        int hashkey = reHash(key);
        buckets[hashkey].addLast(nuevo); 
        cant++;  
        return nuevo;
    }

    public Entry<K,V> remove(Entry<K,V> en){
        if(en == null || en.getKey()== null){throw new InvalidEntryException("invalido");}
        int hashkey = reHash(en.getKey());
        Entry<K,V> toret = null;
        for (Position<Entrada<K,V>> entradas : buckets[hashkey].positions()) {
            if(entradas.element().equals(en)){
                buckets[hashkey].remove(entradas);
                toret = entradas.element();
                cant--;
                break;
            }
        }
        if(toret == null)throw new InvalidEntryException("No se encuentra en el diccionario");
        return toret;
    }

    public Iterable<Entry<K,V>> entries(){
        TdaLista<Entry<K,V>> toret = new TdaLista<Entry<K,V>>();
        for (PositionList<Entrada<K,V>> poslis : buckets) {
            for (Entrada<K,V> entrada : poslis) {
                toret.addLast(entrada);
            }
        }
        return toret;
        
    }







}
