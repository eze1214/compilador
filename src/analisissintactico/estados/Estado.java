/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author alumno
 */
abstract public class Estado {
   protected Stack <Terminal> simbolos;
   Estado(Stack <Terminal> simbolos){
       this.simbolos = new Stack();
       simbolos.addAll(simbolos);
   }
   
   abstract public void ejecutar();
}
