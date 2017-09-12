
package analisissintactico;

import analisissintactico.estados.Bloque;
import analisissintactico.estados.Estado;
import analisissintactico.estados.GeneradorEstados;
import common.Terminal;

import java.util.LinkedList;
import java.util.Queue;
import analisissintactico.estados.Error;
/**
 *
 * @author alumno
 */
public class AnalizadorSintactico {
    
    Error error= new Error();
    AnalizadorSintactico(){
        Queue comandos = new LinkedList();
        
        comandos.add(Terminal.BEGIN);
        comandos.add(Terminal.CALL);
        comandos.add(Terminal.IDENT);
        comandos.add(Terminal.IDENT);
        comandos.add(Terminal.PUNTO_COMA);
        comandos.add(Terminal.NUMERO);
        GeneradorEstados generador = new GeneradorEstados();
        Queue <Estado> estados = generador.determinar(comandos);
        System.out.println(estados);
        while (!estados.isEmpty() && error != null){
            System.out.println("1");
            System.out.println (error = estados.poll().ejecutar());
        }
        //System.out.println("Impreso desde afuera " + error.get());
    }
}
