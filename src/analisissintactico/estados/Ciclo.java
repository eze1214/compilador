/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ezequiel
 */

//Componentes se refiere a la lista que contiene los elementos del ciclo
//Simbolos son los del analizador lexico
public class Ciclo {
    
    private class ListaCircular<T> extends LinkedList <T>{
        public Queue <T> componentes;
        
        @Override
        public int size(){
            return componentes.size();
        }
        ListaCircular(Queue <T> componentes){
            this.componentes = componentes;
        }
        private class IteratorCircular<T> implements Iterator<T>{
        private Integer pos;
        public Iterator<Terminal> it;
        
        IteratorCircular(){
            pos = 0;
            it =  (Iterator<Terminal>) componentes.iterator();
        }

        public Integer getPos(){
            return pos;
        }
        
        public T get(int pos){
            T elemento = this.next();
            while (pos != this.getPos()){
                elemento = this.next();
            }
            return elemento;
        }
            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        @Override
            public String toString(){
                throw new UnsupportedOperationException("No soportado String");
            }
            @Override
            public T next() {
                if(!it.hasNext()){
                    it =  (Iterator<Terminal>) componentes.iterator();
                    pos = 0;
                    return (T) it.next();
                } else {
                    pos = pos +1;
                    return (T) it.next();
                }
            }
        }
       

       @Override
       public Iterator<T> iterator() {
           Iterator it = new IteratorCircular();
           return it;
       }
    }
    protected final Queue <Terminal> componentes;
    protected final Queue <String> mensajes;
    protected final Terminal cont;
    protected final Terminal fin;
    
    protected Terminal simbolo;
    protected Error error;
    protected Boolean terminar;
    protected Integer contador;
    protected Iterator<Terminal> itComp;
    protected Iterator <String> itMsg;
    
    protected void chequearCiclo(){
        if (simbolo != itComp.next()){
                error = new Error(itMsg.next());
                    terminar = true;
                } else {
                  contador ++;
                  itMsg.next();
                }
    }
    
    protected void decidirSiSeguir(Queue<Terminal> simbolos){
        if (simbolo == fin){
                    error = new Error();
                    terminar = true;
                    } else if(simbolo == cont) {
                    contador = 0; 
                    }else{
                       error = new Error("Se esperaba "+ fin);
                       simbolos.add(simbolo);
                       terminar = true;
                }
    }
    
    public  Ciclo(Queue <Terminal> componentes, Queue <String> mensajes, Terminal cont, Terminal fin){
        this.componentes = new ListaCircular(componentes);
        this.mensajes = new ListaCircular(mensajes);
        this.cont = cont;
        this.fin =  fin;
    }
    public Error run(Queue <Terminal> simbolos){
        //System.out.println(componentes);
    itComp = componentes.iterator();
        itMsg = mensajes.iterator();
        error = new Error();
        terminar = false;
        contador = 0;
        while (!simbolos.isEmpty() && !terminar){
            simbolo = simbolos.poll();
            if (contador < componentes.size()){
                chequearCiclo();
            } else {
                decidirSiSeguir(simbolos);
            }
        }
        
        return error;
    }
}