/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import java.io.IOException;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Termino extends Estado {

    public Termino(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }

   @Override
    public Error ejecutar(){
        Estado estado = new Factor(parser,listaErrores);
        error = estado.ejecutar();
        if (error == null){
            try {
                parser.escanear();
            } catch (IOException ex) {
                Logger.getLogger(Termino.class.getName()).log(Level.SEVERE, null, ex);
            }
                switch(parser.getT()){
                    case POR:
                    case DIVIDIDO:
                        ejecutar();
                        break;
                    default:
                        break;
                }
        }
        return error;
    }
}
