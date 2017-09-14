/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.clientefechawsmvn;

import com.curso.fechawsmvn.FechaWebService_Service;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khepherer
 */
public class Principal {

    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

    public static void main(String... args) {
        System.out.println(fecha().orElse("Ha habido un problema"));
        System.out.println("Ejecutando método OneWay. Ver el log del servidor");
        procesoBatch();
    }

    private static Optional<String> fecha() {
        try {
            return Optional.of(
                    new FechaWebService_Service()
                    .getFechaWebServicePort().fecha().toString());
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.toString());
            return Optional.empty();
        }
    }

    private static void procesoBatch() {
        try {
            new FechaWebService_Service().getFechaWebServicePort().procesoBatch();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.toString());
        }
    }
}
