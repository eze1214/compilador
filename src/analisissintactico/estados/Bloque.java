/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.Queue;

/**
 *
 * @author alumno
 */
public class Bloque extends Estado{

    public Bloque(Queue<Terminal> simbolos) {
        super(simbolos);
        System.out.println(simbolos);
    }
    
    boolean seguir;
    @Override
    public void ejecutar(){
        switch (simbolos.poll()) {
            case CONST:
                do{
                    seguir = false;
                    System.out.println("Comienta el ciclo");
                    if (simbolos.poll() == Terminal.IDENT && 
                        simbolos.poll() == Terminal.IGUAL &&
                        simbolos.poll() == Terminal.NUMERO){
                            System.out.println("Termina el ciclo"+simbolos.get(0));
                    }
                    if (simbolos.get(0) == Terminal.COMA){
                        seguir = true;
                        simbolos.poll();}
                } while(seguir);
                if (simbolos.poll() == Terminal.PUNTO_COMA){
                    System.out.println("termino el ciclo bien");
                } else System.out.println("Termino el ciclo mal");
                break;
            case VAR:
                break;
            case PROCEDURE:
                break;
            default:
                break;
        }
    }
}
