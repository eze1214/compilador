/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import common.Terminal;
import java.io.IOException;
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
        public class IteratorCircular<T> implements Iterator<T>{
        private Integer pos;
        public Iterator<Terminal> it;
        
        IteratorCircular(){
            pos = 0;
            it =  (Iterator<Terminal>) componentes.iterator();
        }

        public Integer getPos(){
            return pos;
        }
        
        public void reset(){
            it =  (Iterator<Terminal>) componentes.iterator();
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
    protected Terminal simbComparar; //Utilizado para leer de la lista de comparadores
    protected AnalizadorLexico parser;
    
    protected void chequearCiclo() throws IOException{
        if (simbolo != simbComparar){
                error = new Error(itMsg.next());
                terminar = true;
                } else {
                  contador ++;
                  itMsg.next();
                }
    }
    
    
    protected void decidirSiSeguir() throws IOException{
        if (simbolo == fin){
                    error = null;
                    terminar = true;
                    } else if(simbolo == cont) {
                    contador = 0;
                    itComp = componentes.iterator();
                    }else if (fin == Terminal.CERRADO){
                       error = null;
                       terminar = true;
                    } else{
                       error = new Error("Se esperaba "+ fin);
                       terminar = true;
                }
    }
    
    public  Ciclo(Queue <Terminal> componentes, Queue <String> mensajes, Terminal cont, Terminal fin,AnalizadorLexico parser){
        this.componentes = new ListaCircular(componentes);
        this.mensajes = new ListaCircular(mensajes);
        this.cont = cont;
        this.fin =  fin;
        this.parser = parser;
    }
    public Error run(Queue <Error> listaErrores) throws IOException{
        itComp = componentes.iterator();
        itMsg = mensajes.iterator();
        error = new Error();
        terminar = false;
        contador = 0;
        while ((parser.getT() != Terminal.EOF) && !terminar){
            parser.escanear();
            simbolo = parser.getT();
            simbComparar = itComp.next();
            //System.out.println("Simbolo "+ simbolo + " Simbolo a comparar " + simbComparar);
            if (contador < componentes.size()){
                chequearCiclo();
            } else {
                decidirSiSeguir();
            }
        }
        
        return error;
    }
}