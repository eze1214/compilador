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
    
    private Estado estado;
    GeneradorEstados generador;
    public CicloSaltos(Queue<Terminal> componentes, Queue<String> mensajes, Terminal cont, Terminal fin) {
        super(componentes, mensajes, cont, fin);
    }
    
    private void ejecutarEstado(){
        error = estado.ejecutar();
        System.out.println("Error de estado ejecutado"+ error.get());
        if (error.get() != null){
            terminar =  true;
        }
    }
    @Override
    public Error run(Queue <Terminal> simbolos){
        generador =  new GeneradorEstados();
        System.out.println("Ciclo saltos");
        itComp = componentes.iterator();
        itMsg = mensajes.iterator();
        error = new Error();
        terminar = false;
        contador = 0;
        
        while (!simbolos.isEmpty() && !terminar){
            simbolo = simbolos.poll();
            simbComparar = itComp.next();
            System.out.println(simbComparar);
            if ((estado = generador.run(simbComparar,simbolos)) != null ) {//TODO no va simbolo va itCOMP.next();
                System.out.println("se ejecuta un estado" + estado.getTipo());
                ejecutarEstado();
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
