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

/**
 *
 * @author alumno
 */
public class Bloque extends Estado{

    public Bloque(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }
    
    private void fVar() throws IOException{
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        
        parser.escanear();
        
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA,parser);
        
        error = ciclo.run(listaErrores);
        
    }
    
    
    private void fConst() throws IOException{
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA,parser);
        
            error = ciclo.run(listaErrores);
    }
    
    //Terminar
    private void fProcedure() throws IOException{
        System.out.println("PROCEDURE");
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.PUNTO_COMA);
        nodos.add(Terminal.BLOQUE);
        
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un punto y coma");
        mensajes.add("Se esperaba un bloque");
        mensajes.add("Se esperaba un punto y coma");
        Ciclo ciclo = new CicloSaltos(nodos,mensajes,Terminal.CERRADO,Terminal.PUNTO_COMA,parser);
        
        error = ciclo.run(listaErrores);

    }
    
    @Override
    public Error ejecutar() throws IOException{
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
                GeneradorEstados generador =  new GeneradorEstados();
                if (generador.isProposicion(parser.getT())){
                    Estado estado = new Proposicion(parser,listaErrores);
                    error = estado.ejecutar();
                } else 
                error = new Error("Se esperaba un bloque");
        }
        return error;
    }
}
