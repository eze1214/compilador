/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author alumno
 */
abstract public class Estado {
   protected LinkedList <Terminal> simbolos;
   Estado(Queue <Terminal> simbolos){
       this.simbolos = new LinkedList(simbolos);
       simbolos.addAll(simbolos);
   }
   
   abstract public void ejecutar();
}
