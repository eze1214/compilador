/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author ezequiel
 */

//Componentes se refiere a la lista que contiene los elementos del ciclo
//Simbolos son los del analizador lexico
public class Ciclo {
    private final Queue <Terminal> componentes;
    private final Queue <String> mensajes;
    private final Terminal cont;
    private final Terminal fin;
    
    public  Ciclo(Queue <Terminal> componentes, Queue <String> mensajes, Terminal cont, Terminal fin){
        this.componentes = componentes;
        this.mensajes = mensajes;
        this.cont = cont;
        this.fin =  fin;
    }
    public Error run(Queue <Terminal> simbolos){
        Error errorMsj = new Error();
        boolean error = false;
        boolean seguir = true;
        Iterator <Terminal> itComp = componentes.iterator();
        Iterator <String> itMsg = mensajes.iterator();
        Terminal simbolo;
        do {    
            while (itComp.hasNext() && !simbolos.isEmpty() && !error){
                simbolo = itComp.next();    
                if (simbolos.poll() != simbolo){
                    error = true;
                    errorMsj = new Error (itMsg.next());
                } else{
                    itMsg.next();
                }
            }
            
            if (!error){
                simbolo = simbolos.poll();
                if (simbolo != cont){
                    seguir = false;
                    simbolos.add(simbolo);
                } else {
                    itComp = componentes.iterator();
                    itMsg = mensajes.iterator();
                }
            }
        } while (seguir && !error);
            simbolo = simbolos.poll();
        if (simbolo != fin && !error){
            errorMsj = new Error("Error se esperaba " + fin);
            simbolos.add(simbolo);
        } else if (!error){
            errorMsj = new Error();
        }
        return errorMsj;
    }
}