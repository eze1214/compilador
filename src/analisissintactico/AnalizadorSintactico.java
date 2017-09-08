
package analisissintactico;

import analisissintactico.estados.Bloque;
import analisissintactico.estados.Estado;
import analisissintactico.estados.Programa;
import analisissintactico.estados.Terminal;
import java.util.Stack;

/**
 *
 * @author alumno
 */
public class AnalizadorSintactico {
    private Programa estado;
    
    AnalizadorSintactico(){
        Stack comandos = new Stack();
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
