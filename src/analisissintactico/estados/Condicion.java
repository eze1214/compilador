/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import java.io.IOException;
import java.util.Queue;

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
    private void fODD() throws IOException{
    Estado estado = new Expresion(parser,listaErrores);
    error = estado.ejecutar();
    }
    
    @Override
    public Error ejecutar() throws IOException{
        switch (parser.getT()) {
            case ODD:
                fODD();
                break;
            default:
                fExpresion();
                break;
        }
        return error;
    }

    

}
