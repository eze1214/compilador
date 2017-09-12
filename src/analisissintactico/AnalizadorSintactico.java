
package analisissintactico;

import analisissintactico.estados.Bloque;
import analisissintactico.estados.Estado;
import common.Terminal;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author alumno
 */
public class AnalizadorSintactico {
    
    AnalizadorSintactico(){
        Queue comandos = new LinkedList();
        comandos.add(Terminal.PROCEDURE);
        comandos.add(Terminal.IDENT);
        comandos.add(Terminal.IGUAL);
        comandos.add(Terminal.NUMERO);
        comandos.add(Terminal.NUMERO);
        comandos.add(Terminal.NUMERO);
        Estado estado1 =  new Bloque(comandos);
        estado1.ejecutar();
    }
}
