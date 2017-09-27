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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezequiel
 */
public class Expresion extends Estado{

    public Expresion(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }
    
    private void fMasMenos() throws IOException{
            Estado estado = new Termino(parser,listaErrores);
            error = estado.ejecutar();
            if (error != null) {
                parser.escanear();
                switch(parser.getT()){
                    case MAS:
                    case MENOS:
                        fMasMenos();
                    break;
                }
            }
    }
    
    @Override
    public Error ejecutar(){
        switch (parser.getT()) {
            case MAS:
            case MENOS:
        {
            try {
                fMasMenos();
            } catch (IOException ex) {
                Logger.getLogger(Expresion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            default:
            error = new Error("Se esperaba un + o -");
        }
        return error;
    }
    
}
