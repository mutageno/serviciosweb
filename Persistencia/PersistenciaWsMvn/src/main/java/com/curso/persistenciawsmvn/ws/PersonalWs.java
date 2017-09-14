/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.persistenciawsmvn.ws;

import com.curso.persistenciawsmvn.modelo.Persona;
import java.util.List;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "PersonalWs")
@Stateless()
public class PersonalWs {

    @PersistenceContext(unitName = "com.curso_PersistenciaWsMvn_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public String nuevaPersona(final Persona persona) {
        em.persist(persona);
        return "Nueva persona con id " + persona.getId() + " creada";
    }

    public List<Persona> todas() {
        return em.createQuery("select p from Persona p").getResultList();
    }
}
