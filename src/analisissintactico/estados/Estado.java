/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;

/**
 *
 * @author alumno
 */
abstract public class Estado {
    
   protected Error error;
   protected AnalizadorLexico parser;
   Estado(AnalizadorLexico parser){
       this.parser = parser;
   }
   
   public String getTipo(){
       return this.getClass().getName();
   }
   
   abstract public Error ejecutar();
}
