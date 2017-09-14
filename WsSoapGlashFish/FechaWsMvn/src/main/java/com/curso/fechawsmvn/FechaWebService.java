/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.fechawsmvn;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.HandlerChain;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author khepherer
 */
@WebService(serviceName = "FechaWebService")
@HandlerChain(file = "FechaWebService_handler.xml")
public class FechaWebService {

    private static final Logger LOG = Logger.getLogger(FechaWebService.class.getName());

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "fecha")
    public Date fecha() {
        LOG.log(Level.INFO, "En m\u00e9todo fecha(): {0}", Thread.currentThread().getName());
        return new Date();
    }

    @Oneway
    public void procesoBatch() {
        LOG.log(Level.INFO, "En m\u00e9todo procesoBacth(): {0}", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FechaWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(Level.INFO, "Método ejecutado");
    }
}
