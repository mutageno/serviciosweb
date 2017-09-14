/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.clientesoapasyncmvn;

import com.curso.ejemplosoapasyncmvn.Factura;
import com.curso.ejemplosoapasyncmvn.SoapAsyncWebService;
import com.curso.ejemplosoapasyncmvn.SoapAsyncWebService_Service;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khepherer
 */
public class Principal {

    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            SoapAsyncWebService_Service service = new SoapAsyncWebService_Service();
            SoapAsyncWebService port = service.getSoapAsyncWebServicePort();
            Factura f = new Factura();
            f.setImporte(1000d);
            CompletableFuture.supplyAsync(
                    () -> port.procesarFactura(f)
            ).thenApplyAsync(
                    ident -> port.obtenerFactura(ident)
            ).whenCompleteAsync(
                    (f1, ex) -> LOG.log(Level.INFO, "Factura con id {0} e importe {1}.", new Object[]{f1.getId(), f1.getImporte()})
            ).join();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error: {0}", ex);
        }        
    }

}
