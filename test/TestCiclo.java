/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import analisissintactico.estados.Ciclo;
import analisissintactico.estados.Error;
import common.Terminal;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ezequiel
 */
public class TestCiclo {
    
    public TestCiclo() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCiclo(){
        Queue <Terminal> simbolos =  new LinkedList();
        simbolos.add(Terminal.IDENT);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.NUMERO);
        simbolos.add(Terminal.PUNTO_COMA);
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        assertEquals(error.get(),null);
    }
    
    @Test
    public void testCicloConError(){
        Queue <Terminal> simbolos =  new LinkedList();
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.NUMERO);
        simbolos.add(Terminal.PUNTO_COMA);
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        assertEquals(error.get(),"Se esperaba un identificador");
    }
    
    @Test
    public void testCicloConAlFinal(){
        Queue <Terminal> simbolos =  new LinkedList();
        simbolos.add(Terminal.IDENT);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.NUMERO);
        simbolos.add(Terminal.NUMERO);
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        assertEquals(error.get(),"Error se esperaba "+ Terminal.PUNTO_COMA);
    }
    
    @Test
    public void testCicloError1(){
        Queue <Terminal> simbolos =  new LinkedList();
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.NUMERO);
        simbolos.add(Terminal.COMA);
        simbolos.add(Terminal.NUMERO);
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        assertEquals(error.get(),"Se esperaba un identificador");
    }
    
        @Test
    public void testCicloError1UnaVuelta(){
        Queue <Terminal> simbolos =  new LinkedList();
        simbolos.add(Terminal.IDENT);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.NUMERO);
        simbolos.add(Terminal.COMA);
        simbolos.add(Terminal.NUMERO);
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        assertEquals(error.get(),"Se esperaba un identificador");
    }


    
    
        @Test
    public void testCicloErrorFaltaComa(){
        Queue <Terminal> simbolos =  new LinkedList();
        simbolos.add(Terminal.IDENT);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.NUMERO);
        simbolos.add(Terminal.NUMERO);
        simbolos.add(Terminal.NUMERO);
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        assertEquals(error.get(),"Error se esperaba "+ Terminal.PUNTO_COMA);
    }
    
        public void testCicloError3(){
        Queue <Terminal> simbolos =  new LinkedList();
        simbolos.add(Terminal.IDENT);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.IGUAL);
        simbolos.add(Terminal.COMA);
        simbolos.add(Terminal.NUMERO);
        Queue <Terminal> nodos = new LinkedList();
        nodos.add(Terminal.IDENT);
        nodos.add(Terminal.IGUAL);
        nodos.add(Terminal.NUMERO);
        Queue <String> mensajes = new LinkedList();
        mensajes.add("Se esperaba un identificador");
        mensajes.add("Se esperaba un igual");
        mensajes.add("Se esperaba un numero");
        Ciclo ciclo = new Ciclo(nodos,mensajes,Terminal.COMA,Terminal.PUNTO_COMA);
        Error error = ciclo.run(simbolos);
        assertEquals(error.get(),"Se esperaba un numero");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
