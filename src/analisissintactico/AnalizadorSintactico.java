
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
        comandos.add(Terminal.CONST);
        comandos.add(Terminal.IDENT);
        comandos.add(Terminal.IGUAL);
        comandos.add(Terminal.NUMERO);
        comandos.add(Terminal.COMA);
        comandos.add(Terminal.IDENT);
        comandos.add(Terminal.IGUAL);
        comandos.add(Terminal.NUMERO);
        comandos.add(Terminal.PUNTO_COMA);
        
        Estado estado1 =  new Bloque(comandos);
        estado1.ejecutar();
    }
}
