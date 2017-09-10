/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisissintactico.estados;

/**
 *
 * @author ezequiel
 */
public class Error {
    private String msj;
    
    Error (String msj){
        this.msj = msj;
    };
    
    Error(){
        msj = null;
    }
    @Override
    public String toString(){
        return msj;
    }
   
    public String get(){
        return msj;
    }
    
}
