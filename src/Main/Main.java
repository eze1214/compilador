/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import analisislexico.AnalizadorLexico;
import analisissintactico.AnalizadorSintactico;
import common.Terminal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Main {
  public static void main(String[] args) {
      try {   
          AnalizadorSintactico analizador =  new AnalizadorSintactico();
      } catch (IOException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
}
}