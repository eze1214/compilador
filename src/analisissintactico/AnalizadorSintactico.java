
package analisissintactico;

import analisislexico.AnalizadorLexico;
import analisissintactico.estados.Bloque;
import analisissintactico.estados.Estado;
import analisissintactico.estados.GeneradorEstados;
import common.Terminal;

import java.util.LinkedList;
import java.util.Queue;
import analisissintactico.estados.Error;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author alumno
 */
public class AnalizadorSintactico {
    
    BufferedReader buffer;
    AnalizadorLexico analizador;
    
    Error error= new Error();
    
    public AnalizadorSintactico() throws FileNotFoundException, IOException{
        String path = new File("").getAbsolutePath();
        String archivo = "\\src\\prueba.txt";
        buffer = new BufferedReader(new FileReader(path + archivo));
        analizador = new AnalizadorLexico(buffer);

        GeneradorEstados generador = new GeneradorEstados();
        analizador.escanear();
        System.out.println(analizador.getT());
        Queue <Estado> estados = generador.determinar(analizador.getT(),analizador);
        System.out.println(" los estados son " + estados);
        while (!estados.isEmpty() && error != null){
            System.out.println("1");
            System.out.println (error = estados.poll().ejecutar());
        }
        //System.out.println("Impreso desde afuera " + error.get());
    }
}
