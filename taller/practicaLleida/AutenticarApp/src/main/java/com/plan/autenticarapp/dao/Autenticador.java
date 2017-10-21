/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.autenticarapp.dao;

import com.plan.autenticarapp.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author khepherer
 */
@Stateless
public class Autenticador {

    @PersistenceContext(unitName = "com.plan_AutenticarApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public Usuario nuevo(String nombre, String clave) {
        Usuario u = new Usuario(nombre,clave);
        em.persist(u);
        return u;
    }

    public Usuario autenticarPorNombreYClave(String nombre, String clave) {
        try {
            TypedQuery<Usuario> q = em.createQuery("select u from Usuario u where u.nombre=:nombre and u.clave=:clave", Usuario.class);
            q.setParameter("nombre", nombre);
            q.setParameter("clave", clave);
            Object singleResult = q.getSingleResult();
            return q.getSingleResult();
        } catch (Exception e) {
            Usuario u = new Usuario("no_existe", "no_existe");
            u.setId(0L);
            return u;
        }
    }
}
