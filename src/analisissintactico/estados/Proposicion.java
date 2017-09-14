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
            default:
                break;
        }
        return error;
    }

    private void fBegin() {
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.PROPOSICION);
        
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba una proposicion");
       
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.PUNTO_COMA,Terminal.END,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Proposicion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        
    }
    
    private void fWhile(){
        
    }
}
