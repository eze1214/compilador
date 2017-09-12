
package analisissintactico;

import analisissintactico.estados.Bloque;
import analisissintactico.estados.Estado;
import analisissintactico.estados.GeneradorEstados;
import common.Terminal;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author alumno
 */
public class AnalizadorSintactico {
    
    analisissintactico.estados.Error error;
    AnalizadorSintactico(){
        Queue comandos = new LinkedList();
        
        comandos.add(Terminal.BEGIN);
        comandos.add(Terminal.CALL);
        comandos.add(Terminal.IDENT);
        comandos.add(Terminal.END);
        comandos.add(Terminal.PUNTO_COMA);
        comandos.add(Terminal.NUMERO);
        GeneradorEstados generador = new GeneradorEstados();
        Queue <Estado> estados = generador.determinar(comandos);

        while (!estados.isEmpty() && error == null){
            error = estados.poll().ejecutar();
        }
        //System.out.println("Impreso desde afuera " + error.get());
    }
}
