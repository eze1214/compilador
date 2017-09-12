/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import static common.Terminal.EXPRESION;
import java.util.Queue;

/**
 *
 * @author ezequiel
 */
public class GeneradorEstados {
 
    public Estado determinar(Queue <Terminal> comandos){
    Estado estado = null;
    Terminal terminal = comandos.peek();
    switch (terminal){
        case CONST:
        case PROCEDURE:
        case VAR:    
            estado =  new Bloque(comandos);
            break;
        case IDENT:
        case CALL:
        case BEGIN:
        case IF:
        case WHILE:
            estado =  new Proposicion(comandos);
        default:
            break;
    }
     return estado;
 }
    
    public Estado run(Terminal t,Queue <Terminal> comandos){
    Estado estado = null;
    comandos.add(t);//TODO esto me parece que no va porque estoy mezclando las dos listas
     switch (t){
         case BLOQUE: 
            estado =  new Bloque(comandos);
            break;
         case PREPOSICION:
            estado =  new Proposicion(comandos);
         case EXPRESION:
             estado = new Expresion(comandos);
        default:
            break;
    }
     return estado;
 }  
}
