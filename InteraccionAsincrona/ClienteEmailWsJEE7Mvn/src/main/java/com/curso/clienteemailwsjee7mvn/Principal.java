/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.clienteemailwsjee7mvn;

import com.curso.emailwsjee7mvn.ws.EmailWebService;
import com.curso.emailwsjee7mvn.ws.EmailWebService_Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Principal {

    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try { // Call Web Service Operation
            EmailWebService_Service service = new EmailWebService_Service();
            EmailWebService port = service.getEmailWebServicePort();
            String resultado = port.email("prueba");
            LOG.log(Level.INFO, "Resultado = {0}", resultado);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error: {0}", ex);
        }
    }
}
