/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ezequiel
 */
public class Proposicion extends Estado {

    public Proposicion(Queue<Terminal> simbolos) {
        super(simbolos);
    }

    private void fIdent(){

    Queue <Terminal> nodos = new LinkedList();
    nodos.add(Terminal.ASIGNACION);
    nodos.add(Terminal.EXPRESION);
    
    Queue <String> mensajes = new LinkedList();
    mensajes.add("Se esperaba una asignacion");
    mensajes.add("Se esperaba una expresion");

    Ciclo ciclo = new CicloSaltos(nodos,mensajes,Terminal.CERRADO,Terminal.CERRADO);
    error = ciclo.run(simbolos);
    
    }
    @Override
    public Error ejecutar() {
        switch (simbolos.poll()) {
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
       
        Ciclo ciclo = new CicloSaltos(nodos,mensajes,Terminal.PUNTO_COMA,Terminal.END);
        error = ciclo.run(simbolos);
    }

    private void fCall() {
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);

        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba una identificador");

        Ciclo ciclo = new CicloSaltos(nodos,mensajes,Terminal.CERRADO,Terminal.PUNTO_COMA);
        error = ciclo.run(simbolos);
    }
    
    private void fIf(){
        
    }
    
    private void fWhile(){
        
    }
}
