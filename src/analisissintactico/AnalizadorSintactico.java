
package analisissintactico;

import analisislexico.AnalizadorLexico;
import analisissintactico.estados.Bloque;
import analisissintactico.estados.Estado;
import analisissintactico.estados.GeneradorEstados;
import common.Terminal;

import java.util.LinkedList;
import java.util.Queue;
import analisissintactico.estados.Error;
import analisissintactico.estados.Programa;
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
    Queue listaErrores;
    Error error= new Error();
    
    public AnalizadorSintactico() throws FileNotFoundException {
        listaErrores = new LinkedList();
        String path = new File("").getAbsolutePath();
        String archivo = "/src/prueba.txt";
        buffer = new BufferedReader(new FileReader(path + archivo));
        analizador = new AnalizadorLexico(buffer);

        
        //while (!estados.isEmpty() && error != null){
        //    System.out.println("1");
        //    System.out.println (error = estados.poll().ejecutar());
        //}
        //System.out.println("Impreso desde afuera " + error.get());
    }
    
    public void run() throws IOException{
        GeneradorEstados generador = new GeneradorEstados();
        Estado estado = new Programa(analizador,listaErrores);
        estado.ejecutar();
    }
 }
