/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import common.Terminal;
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
    
    public boolean isBloque(Terminal terminal){
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
    
    public boolean isProposicion(Terminal terminal){
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
    
    public boolean isExpresion(Terminal terminal){
        boolean result =  false;
        switch(terminal){
            case MAS:
            case MENOS:
        result = true;
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
 * @param terminal lista de comandos
 * @param parser parser lexico jflex
 * @return Lista de estados que deben ser ejecutados
 */    
    //Discontinuada borrar en algun momento
    public Queue <Estado> determinar(Terminal terminal,AnalizadorLexico parser, Queue <Error> listaErrores){
        Queue estados =  new LinkedList(); 
        if(isBloque(terminal)){
            Estado estado =  new Bloque(parser,listaErrores);
            estados.add(estado);
        }
        if(isProposicion(terminal)){
            Estado estado = new Proposicion(parser,listaErrores);
            estados.add(estado);
        }
        return estados;
 }
    
    /**
     * Genera un bloque a partir de una orden dada como un terminal
     * @param t La orden dada por Terminal
     * @param parser
     * @param listaErrores 
     * @return 
     */
    public Estado run(Terminal t,AnalizadorLexico parser, Queue <Error> listaErrores){
    Estado estado = null;
     switch (t){
         case BLOQUE: 
            estado =  new Bloque(parser,listaErrores);
            break;
         case PROPOSICION:
            estado =  new Proposicion(parser,listaErrores);
            break;
         case EXPRESION:
             estado = new Expresion(parser,listaErrores);
             break;
         case CONDICION:
             estado  = new Condicion(parser,listaErrores);
             break;
         case TERMINO:
             estado = new Termino(parser,listaErrores);
             break;
         case FACTOR:
             estado = new Factor(parser,listaErrores);
             break;
        default:
            break;
    }
     return estado;
 }  
}
