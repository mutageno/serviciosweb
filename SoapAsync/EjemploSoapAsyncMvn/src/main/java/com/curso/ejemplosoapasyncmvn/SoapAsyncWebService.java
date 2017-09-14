/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplosoapasyncmvn;

import com.curso.ejemplosoapasyncmvn.modelo.Factura;
import com.curso.ejemplosoapasyncmvn.repo.Repositorio;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author khepherer
 */
@WebService(serviceName = "SoapAsyncWebService")
@Stateless()
public class SoapAsyncWebService {

    private static final Logger LOG = Logger.getLogger(SoapAsyncWebService.class.getName());

    private final ExecutorService executor = Executors.newCachedThreadPool();
    @Inject
    private Repositorio<Factura> repo;

    @PreDestroy
    private void limpiar() {
        LOG.log(Level.INFO, "Parando el executor...");
        executor.shutdown();
    }

    /**
     * Web service operation
     *
     * @param factura
     * @return
     */
    public Long procesarFactura(final Factura factura) {
        factura.setId(System.currentTimeMillis());
        CompletableFuture.supplyAsync(
                () -> {
                    LOG.log(Level.INFO, "Comenzando proceso asíncrono");
                    factura.setImporte(factura.getImporte() * 2);
                    LOG.log(Level.INFO, "Finalizado proceso asíncrono");
                    return factura;
                }, executor
        ).whenComplete((f, ex) -> repo.guardar(f.getId(), f));
        return factura.getId();
    }

    public Factura obtenerFactura(Long id) {
        return repo.obtener(id).orElse(Factura.ERRORES);
    }
}
