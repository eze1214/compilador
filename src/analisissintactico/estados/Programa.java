package analisissintactico.estados;

import analisislexico.AnalizadorLexico;

public class Programa extends Estado{

    public Programa(AnalizadorLexico parser) {
        super(parser);
    }

    @Override
    public Error ejecutar(){
        return error;
    }
}
