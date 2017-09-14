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
public class Condicion extends Estado {

    public Condicion(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }

    private void fExpresion() throws IOException{
        GeneradorEstados generador = new GeneradorEstados();
                if (generador.isExpresion(parser.getT())){
                    Estado estado = new Expresion(parser,listaErrores);
                    error = estado.ejecutar();
                    parser.escanear();
                    if (error == null){
                        switch (parser.getT()){
                            case IGUAL:
                            case DISTINTO:
                            case MENOR:
                            case MAYOR:
                            case MAYOR_IGUAL:
                            case MENOR_IGUAL:
                                estado = new Expresion(parser,listaErrores);
                                error = estado.ejecutar();
                            break;
                            default:
                                error =  new Error ("Se esperaba un comparador");
                            break;
                        }
                    }
                } else 
                error = new Error("Se esperaba una expresion");
    }
    private void fODD(){
    Estado estado = new Expresion(parser,listaErrores);
    error = estado.ejecutar();
    }
    
    @Override
    public Error ejecutar() {
        switch (parser.getT()) {
            case ODD:
                fODD();
                break;
            default:
        {
            try {
                fExpresion();
            } catch (IOException ex) {
                Logger.getLogger(Condicion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        return error;
    }

    

}
