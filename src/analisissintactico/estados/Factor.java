/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import common.Terminal;
import java.io.IOException;
import java.util.Queue;

/**
 *
 * @author alumno
 */
public class Factor extends Estado {

    public Factor(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }

    @Override
    public Error ejecutar() throws IOException{
            switch(parser.getT()){
                case IDENT:
                case NUMERO:
                    break;
                case ABRE_PARENTESIS:
                    Estado estado =  new Expresion(parser,listaErrores);
                    error = estado.ejecutar();
                    listaErrores.add(error);
                    parser.escanear();
                    if (parser.getT() != Terminal.CIERRA_PARENTESIS){
                       listaErrores.add(new Error("Se esperaba un parentesis de cierre"));
                    }
            }
        return error;
    }
}
