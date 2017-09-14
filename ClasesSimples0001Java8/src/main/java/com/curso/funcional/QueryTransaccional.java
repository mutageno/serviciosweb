/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.funcional;

import java.util.Collection;
import java.util.function.BiFunction;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 * @param <T>
 */
@FunctionalInterface
public interface QueryTransaccional<T> extends BiFunction<String, Session, Collection<T>> {
}
