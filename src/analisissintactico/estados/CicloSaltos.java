/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.LinkedList;
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
        System.out.println("Error de estado ejecutado");
        if (error == null){
            //terminar =  true;
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
            simbComparar = itComp.next();
            simbolo = simbolos.poll();
            System.out.println(simbComparar);
            if ((estado = generador.run(simbComparar,simbolos)) != null ) {//TODO no va simbolo va itCOMP.next();
                insertar(simbolo,simbolos); ;//Lo agrego porque ahora es responsabilidad del estado 
                estado = generador.run(simbComparar,simbolos);
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
