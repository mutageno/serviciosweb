/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplosoapasyncmvn.repo;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

/**
 *
 * @author usuario
 * @param <T>
 */
@Singleton
public class Repositorio<T> {

    private static final Logger LOG = Logger.getLogger(Repositorio.class.getName());

    private final Map<Long, T> repo = new ConcurrentHashMap<>();

    public void guardar(final Long id, final T f) {
        repo.put(id, f);
    }

    @PreDestroy
    private void limpiar() {
        LOG.log(Level.INFO,"Limpiando el repo de facturas");
        repo.clear();
    }

    public Optional<T> obtener(final Long id) {
        return Optional.ofNullable(repo.get(id));
    }
}
