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
 * @author alumno
 */
public class Bloque extends Estado{
    
    public Bloque(Queue<Terminal> simbolos) {
        super(simbolos);
        System.out.println(simbolos);
    }
    
    private void fVar(){
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        error = ciclo.run(simbolos);
    }
    
    
    private void fConst(){
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        error = ciclo.run(simbolos);
        System.out.println(error);
        System.out.println(simbolos);
    }
    
    //Terminar
    private void fProcedure(){
        System.out.println("PROCEDURE");
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.PUNTO_COMA);
        nodos.add(Terminal.BLOQUE);
        nodos.add(Terminal.PUNTO_COMA);
        
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un punto y coma");
        mensajes.add("Se esperaba un bloque");
        mensajes.add("Se esperaba un punto y coma");
        Ciclo ciclo = new CicloSaltos(nodos,mensajes,Terminal.CERRADO,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        System.out.println(error);
    }
    
    @Override
    public Error ejecutar(){
        switch (simbolos.poll()) {
            case CONST:
                fConst();
                break;
            case VAR:
                fVar();
                break;
            case PROCEDURE:
                fProcedure();
                break;
            default:
                error = new Error();
        }
        return error;
    }
}
