/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.Queue;

/**
 *
 * @author ezequiel
 */
public class Expresion extends Estado{

    public Expresion(Queue<Terminal> simbolos) {
        super(simbolos);
    }

    @Override
    public Error ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
