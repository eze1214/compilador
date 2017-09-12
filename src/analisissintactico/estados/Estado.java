/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import common.Terminal;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author alumno
 */
abstract public class Estado {
    
   protected Error error;
   protected LinkedList <Terminal> simbolos;
   Estado(Queue <Terminal> simbolos){
       this.simbolos = new LinkedList(simbolos);
   }
   
   abstract public Error ejecutar();
}
