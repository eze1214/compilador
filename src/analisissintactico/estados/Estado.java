/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

import analisislexico.AnalizadorLexico;
import java.io.IOException;
import java.util.Queue;

/**
 *
 * @author alumno
 */
abstract public class Estado  {
    
   protected Error error;
   protected AnalizadorLexico parser;
   protected Queue listaErrores;
   Estado(AnalizadorLexico parser, Queue <Error> listaErrores){
       this.parser = parser;
       this.listaErrores = listaErrores;
   }
   
   public String getTipo(){
       return this.getClass().getName();
   }
   
   abstract public Error ejecutar() throws IOException;
}
