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
 * @author alumno
 */
public class Bloque extends Estado{

    public Bloque(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }
    
    private void fVar(){
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        try {
            parser.escanear();
        } catch (IOException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA,parser);
        try {
            
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(error);
        System.out.println(parser.getT()+" "+parser.getTexto());
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
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.CERRADO,Terminal.PUNTO_COMA,parser);
        try {
            error = ciclo.run(listaErrores);
        } catch (IOException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(error);
    }
    
    @Override
    public Error ejecutar(){
        System.out.println("parser "+ parser.getT());
        switch (parser.getT()) {
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
