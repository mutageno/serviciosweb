/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.clientecachewsmaven;

import java.util.logging.Level;
import java.util.logging.Logger;

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
            ws.CacheWebService_Service service = new ws.CacheWebService_Service();
            ws.CacheWebService port = service.getCacheWebServicePort();
            String resultado = port.cache("uno");
            LOG.log(Level.INFO, "Resultado = {0}", resultado);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error: {0}", ex);
        }

    }
    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

}
