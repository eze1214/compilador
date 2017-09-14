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
            parser.escanear();
            while ((parser.getT() != Terminal.PUNTO) && (parser.getT() != Terminal.EOF)){
                Estado estado = new Bloque(parser,listaErrores);
                System.out.println("Leido "+parser.getT()+" En linea "+parser.getLine());
                error = estado.ejecutar();
                if (error == null){
                    parser.escanear();
                } else
                    listaErrores.add(error);
            }
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
