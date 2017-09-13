/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;

/**
 *
 * @author ezequiel
 */
public class Expresion extends Estado{

    public Expresion(AnalizadorLexico parser) {
        super(parser);
    }

    @Override
    public Error ejecutar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
