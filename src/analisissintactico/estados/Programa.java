package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import common.Terminal;
import java.io.IOException;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa extends Estado{

    public Programa(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }

    @Override
    public Error ejecutar(){
  
        try {
            Terminal prueba;
            parser.escanear();
            do{
                
                
                Estado estado = new Bloque(parser,listaErrores);
                
                prueba = parser.getT();
                if(parser.getT() == Terminal.SALTO_LINEA ){
                    parser.escanear();
                }
                error = estado.ejecutar();
                prueba = parser.getT();
                if (error == null){
                    parser.escanear();
                }
                prueba = parser.getT();
                listaErrores.add(error);
                if(parser.getT() == Terminal.SALTO_LINEA ){
                    parser.escanear();
                }
            }while(parser.getT() != Terminal.PUNTO && parser.getT() != Terminal.EOF);
             } catch (IOException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
                }
            if (parser.getT() != Terminal.PUNTO){
                listaErrores.add (new Error("Se esperaba un punto"));
            }
            System.out.println("Final de errores " + listaErrores);
            return null;
       
        
    }
    
}
