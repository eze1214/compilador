/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import common.Terminal;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezequiel
 */
public class Proposicion extends Estado {

    public Proposicion(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }

    private void fIdent(){

    Queue <Terminal> nodos = new LinkedList();
    nodos.add(Terminal.ASIGNACION);
    nodos.add(Terminal.EXPRESION);
    
    Queue <String> mensajes = new LinkedList();
    mensajes.add("Se esperaba una asignacion");
    mensajes.add("Se esperaba una expresion");

    Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.CERRADO,Terminal.CERRADO,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @Override
    public Error ejecutar() {
        switch (parser.getT()) {
            case IDENT:
                fIdent();
                break;
            case CALL:
                fCall();
                break;
            case BEGIN:
                fBegin();
                break;
            case IF:
                fIf();
                break;
            case WHILE:
                fWhile();
                break;
            case READLN:
                fReadln();
                break;
            case WRITELN:
                fWriteln();
                break;
            case WRITE:
                fWrite();
                break;
            default:
                break;
        }
        return error;
    }

    private void fBegin() {
        Queue <Terminal> nodos = new LinkedList();
        Estado estado = new Proposicion(parser,listaErrores);
        error = estado.ejecutar();
        if (error != null){
            try {
                parser.escanear();
            } catch (IOException ex) {
                Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
            }
            nodos.add(Terminal.PUNTO_COMA);
            nodos.add(Terminal.PROPOSICION);
        
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba una proposicion");
       
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.PUNTO_COMA,Terminal.END,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}

    private void fCall() {
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);

        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba una identificador");

        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.CERRADO,Terminal.CERRADO,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fIf(){
        Queue nodos = new LinkedList();
        nodos.add(Terminal.CONDICION);
        nodos.add(Terminal.THEN);
        nodos.add(Terminal.PROPOSICION);
        
        Queue mensajes = new LinkedList();
        mensajes.add("Se esperaba una condicion");
        mensajes.add("Se esperaba un THEN");
        mensajes.add("Se esperaba una proposicion");
        
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.CERRADO,Terminal.CERRADO,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fWhile(){
        Queue nodos = new LinkedList();
        nodos.add(Terminal.CONDICION);
        nodos.add(Terminal.DO);
        nodos.add(Terminal.PROPOSICION);
        
        Queue mensajes = new LinkedList();
        mensajes.add("Se esperaba una condicion");
        mensajes.add("Se esperaba un DO");
        mensajes.add("Se esperaba una proposicion");
        
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.CERRADO,Terminal.CERRADO,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fReadln() {
        boolean terminar = false;
        try {
            parser.escanear();
         
        if(parser.getT() == Terminal.ABRE_PARENTESIS){
            parser.escanear();
            if (parser.getT() == Terminal.IDENT){
                while (!terminar){
                    if (parser.getT() == Terminal.COMA){
                        parser.escanear();
                        if (parser.getT() == Terminal.IDENT){
                            parser.escanear();
                            if (parser.getT() == Terminal.CIERRA_PARENTESIS){
                                terminar = true;
                            }
                        } else {
                            error = new Error("Se esperaba un identificador");
                            terminar = true;
                        }
                    } else {
                        error =  new Error("Se esperaba una coma");
                        terminar = true;
                    }
                }
            }
        }
        }
        catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fWriteln() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fWrite() {
        boolean seguir = false;
        try { 
            parser.escanear();
            if (parser.getT() == Terminal.ABRE_PARENTESIS){
                parser.escanear();
                if (parser.getT() == Terminal.CADENA_TEXTO){
                    seguir = true;
                } else {
                    Estado estado = new Expresion (parser,listaErrores);
                    if (estado.ejecutar() != null){
                        seguir = true;
                    } 
                }
                 
            }
            while(seguir){
                parser.escanear();
                if (parser.getT() == Terminal.COMA){
                    parser.escanear();
                    if (parser.getT() == Terminal.CADENA_TEXTO){
                        if (parser.getT() == Terminal.CIERRA_PARENTESIS){
                            seguir = false;
                        }
                    } else {
                        Estado expresion = new Expresion(parser,listaErrores);
                        if (expresion.ejecutar() == null){
                            seguir = false;
                            listaErrores.add(new Error ("Se esperaba una cadena o una expresion"));
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
