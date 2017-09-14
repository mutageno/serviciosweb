/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.funcional;

import com.curso.utilidades.HibernateUtil;
import static java.lang.System.out;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 *
 * @author usuario
 */
public class EjecutorTransaccional {

    private static final Logger LOG = Logger.getLogger(EjecutorTransaccional.class.getName());

    /**
     *
     * @param <T>
     * @param mapa
     */
    public static <T> void runEnParalelo(Map<QueryTransaccional<T>, String> mapa) {
        Objects.requireNonNull(mapa);
        mapa.forEach(
                (clave, valor)
                -> CompletableFuture.supplyAsync(
                        () -> {
                            LOG.log(Level.INFO, Thread.currentThread().toString());
                            return runQuery(clave, valor);
                        }
                )
                .whenComplete((r, e) -> out.println(r + " Error:" + e)).join()
        );
    }

    public static <T> Optional<T> run(Transaccional<T> bloque, T objeto) {
        return HibernateUtil.getSessionFactory().
                map(SessionFactory::getCurrentSession).
                map(
                        s -> {
                            try {
                                s.getTransaction().begin();
                                T resultado = bloque.apply(objeto, s);
                                s.getTransaction().commit();
                                return resultado;
                            } catch (HibernateException e) {
                                s.getTransaction().rollback();
                                LOG.log(Level.SEVERE, e.toString());
                            }
                            return objeto;
                        }
                );
    }

    public static <T> Optional<Collection<T>> runQuery(QueryTransaccional<T> bloque, String consulta) {
        return HibernateUtil.getSessionFactory()
                .map(SessionFactory::getCurrentSession)
                .map(
                        s -> {
                            try {
                                s.getTransaction().begin();
                                Collection<T> resultado = bloque.apply(consulta, s);
                                s.getTransaction().commit();
                                return resultado;
                            } catch (HibernateException e) {
                                s.getTransaction().rollback();
                                LOG.log(Level.SEVERE, e.toString());
                                return Collections.emptyList();
                            }
                        }
                );
    }

}
