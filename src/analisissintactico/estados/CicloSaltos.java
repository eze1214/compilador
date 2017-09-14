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
 * @author ezequiel
 */
public class CicloSaltos extends Ciclo {
    
    private Estado estado;
    GeneradorEstados generador;
    public CicloSaltos(Queue<Terminal> componentes, Queue<String> mensajes, Terminal cont, Terminal fin,AnalizadorLexico parser) {
        super(componentes, mensajes, cont, fin,parser);
    }
    
    private void ejecutarEstado(){
        error = estado.ejecutar();
        System.out.println("Error de estado ejecutado");
        if (error == null){
            //terminar =  true;
        }
    }
    

    @Override
    public Error run(Queue <Error> listaErrores) throws  IOException{
        generador =  new GeneradorEstados();
        System.out.println("Ciclo saltos");
        itComp = componentes.iterator();
        itMsg = mensajes.iterator();
        error = new Error();
        terminar = false;
        contador = 0;
        
        while ((parser.getT() != Terminal.EOF) && !terminar){
            simbComparar = itComp.next();
            simbolo = parser.getT();
            System.out.println(simbComparar);
            if ((estado = generador.run(simbComparar,parser,listaErrores)) != null ) {//TODO no va simbolo va itCOMP.next(); 
                //estado = generador.run(simbComparar,simbolos);
                System.out.println("se ejecuta un estado" + estado.getTipo());
                ejecutarEstado();
                contador++;
            } else {
                
            if (contador < componentes.size()){
                chequearCiclo();
            } else {
                decidirSiSeguir();
            }
        }
        }
        
        return error;
    }
}
