package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import common.Terminal;
import java.io.IOException;
import java.util.Queue;

public class Programa extends Estado{

    public Programa(AnalizadorLexico parser, Queue<Error> listaErrores) {
        super(parser, listaErrores);
    }

    
    @Override
    public Error ejecutar() throws IOException{
        parser.escanear();
        while ((parser.getT() != Terminal.PUNTO) && (parser.getT() != Terminal.EOF)){
            Estado estado = new Bloque(parser,listaErrores);
            error = estado.ejecutar();
            if (error == null){
                parser.escanear();
            } else
                listaErrores.add(error);
            
            if (parser.getT() != Terminal.PUNTO){
                listaErrores.add (new Error("Se esperaba un punto"));
                return new Error("Se esperaba un punto");
            }
        }
        return null;
    }
}
