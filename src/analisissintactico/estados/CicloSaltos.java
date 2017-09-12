/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author ezequiel
 */
public class CicloSaltos extends Ciclo {
    
    GeneradorEstados generador;
    public CicloSaltos(Queue<Terminal> componentes, Queue<String> mensajes, Terminal cont, Terminal fin) {
        super(componentes, mensajes, cont, fin);
    }
    
    
    @Override
    public Error run(Queue <Terminal> simbolos){
        generador =  new GeneradorEstados();
        Estado estado;
        System.out.println("Ciclo saltos");
        itComp = componentes.iterator();
        itMsg = mensajes.iterator();
        error = new Error();
        terminar = false;
        contador = 0;
        while (!simbolos.isEmpty() && !terminar){
            simbolo = simbolos.poll();
            if ((estado = generador.run(simbolo,simbolos)) != null ) {//TODO no va simbolo va itCOMP.next();
                error = estado.ejecutar();
                if (error.get() != null){
                    terminar =  true;
                }
            } else {
            if (contador < componentes.size()){
                chequearCiclo();
            } else {
                decidirSiSeguir(simbolos);
            }
        }
        }
        
        return error;
    }
}
