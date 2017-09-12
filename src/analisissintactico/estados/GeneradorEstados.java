/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import static common.Terminal.EXPRESION;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ezequiel
 */
public class GeneradorEstados {
 
    private Queue <Terminal> TerminalesBloque;
    private Queue <Terminal> TerminalesProposicion;
    
    public GeneradorEstados(){
        TerminalesBloque = new LinkedList();
        TerminalesProposicion = new LinkedList();
        
        setupBloque();
        setupPreposicion();
    }
    
    private void setupBloque(){
        TerminalesBloque.add(Terminal.CONST);
        TerminalesBloque.add(Terminal.PROCEDURE);
        TerminalesBloque.add(Terminal.VAR);
    }
    
    private boolean isBloque(Terminal terminal){
        boolean result = false;
        switch(terminal){
            case CONST:
            case PROCEDURE:
            case VAR:    
                result = true;
                break;
            }
        if (isProposicion(terminal)){
            result = true;
        } 
        return result;
    }
    
    private boolean isProposicion(Terminal terminal){
        boolean result = false;
        switch(terminal){
            case IDENT:
            case CALL:
            case BEGIN:
            case IF:
            case WHILE:
                result = true;
                break;
        }
        return result;
    }
    
    private void setupPreposicion(){
        TerminalesProposicion.add(Terminal.IDENT);
        TerminalesProposicion.add(Terminal.CALL);
        TerminalesProposicion.add(Terminal.BEGIN);
        TerminalesProposicion.add(Terminal.IF);
        TerminalesProposicion.add(Terminal.WHILE);
    }

/**
 * Devuelve una lista de estados validos segun la cabeza del comando
 * No cambia la cabeza del comando dentro de comandos
 * @param Lista de comandos
 * @return Lista de estados que deben ser ejecutados
 */    
    public Queue <Estado> determinar(Queue <Terminal> comandos){
        Queue estados =  new LinkedList(); 
        Terminal terminal = comandos.peek();
        if(isBloque(terminal)){
            Estado estado =  new Bloque(comandos);
            estados.add(estado);
        }
        if(isProposicion(terminal)){
            Estado estado = new Proposicion(comandos);
            estados.add(estado);
        }
        return estados;
 }
    
    /**
     * Genera un bloque a partir de una orden dada como un terminal
     * @param t La orden dada por Terminal
     * @param comandos comandos que se le pasa por parametro al estado generado
     * @return 
     */
    public Estado run(Terminal t,Queue <Terminal> comandos){
    Estado estado = null;
     switch (t){
         case BLOQUE: 
            estado =  new Bloque(comandos);
            break;
         case PROPOSICION:
            estado =  new Proposicion(comandos);
            break;
         case EXPRESION:
             estado = new Expresion(comandos);
             break;
        default:
            break;
    }
     return estado;
 }  
}
