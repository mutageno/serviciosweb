/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.jaxwshandlerclient;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        try { // Call Web Service Operation
            com.curso.jaxwshandlerserver.ServerInfo_Service service = new com.curso.jaxwshandlerserver.ServerInfo_Service();
            com.curso.jaxwshandlerserver.ServerInfo port = service.getServerInfoPort();
            // TODO process result here
            java.lang.String result = port.getServerName();
            System.out.println("Resultado = "+result);
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error:" + ex);
        }  
    }
    
}
