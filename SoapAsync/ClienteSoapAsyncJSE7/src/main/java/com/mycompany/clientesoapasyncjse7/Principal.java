/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientesoapasyncjse7;

import com.curso.ejemplosoapasyncmvn.Factura;
import com.curso.ejemplosoapasyncmvn.SoapAsyncWebService;
import com.curso.ejemplosoapasyncmvn.SoapAsyncWebService_Service;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iconotc
 */
public class Principal {

    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SoapAsyncWebService_Service service = new SoapAsyncWebService_Service();
        final SoapAsyncWebService port = service.getSoapAsyncWebServicePort();
        Factura f = new Factura();
        f.setImporte(1000d);
        ExecutorService executor = Executors.newCachedThreadPool();
        System.out.println("El método procesar factura retorna inmediatamente");
        final Long id = port.procesarFactura(f);
        Callable<Factura> callableFactura = new Callable<Factura>() {
            @Override
            public Factura call() throws Exception {
                return port.obtenerFactura(id);
            }
        };
        Future<Factura> resultadoFuturo = executor.submit(callableFactura);
        try {
            System.out.println("El hilo principal se bloquea");
            Factura resultado = resultadoFuturo.get(1, TimeUnit.SECONDS);
            LOG.log(Level.INFO, "Factura desde el servicio web: {0}", resultado.getImporte());
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            LOG.log(Level.SEVERE, "Error", ex);
        }
        System.out.println("Finalizando...");
        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }else{
                LOG.log(Level.INFO, "Terminación normal");
            }
        } catch (InterruptedException e) {
            LOG.log(Level.SEVERE, "Error", e);
            executor.shutdownNow();
        }
    }
}
