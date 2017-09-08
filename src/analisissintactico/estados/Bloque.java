/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import java.util.Stack;

/**
 *
 * @author alumno
 */
public class Bloque extends Estado{

    public Bloque(Stack<Terminal> simbolos) {
        super(simbolos);
    }
    
    Terminal simbolo;
    @Override
    public void ejecutar(){
        switch (simbolos.pop()) {
            case CONST:
                do{
                    System.out.println("Comienta el ciclo");
                if (simbolos.pop() == Terminal.IDENT && 
                    simbolos.pop() == Terminal.IGUAL &&
                    simbolos.pop() == Terminal.NUMERO){
                        System.out.println("Termina el ciclo");
                } }while((simbolo = simbolos.get(0)) == Terminal.COMA);
                if (simbolo == Terminal.PUNTO_COMA){
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
