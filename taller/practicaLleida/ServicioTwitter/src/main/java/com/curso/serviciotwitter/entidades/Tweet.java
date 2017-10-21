/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.serviciotwitter.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author usuario
 */
@Entity
public class Tweet implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String mensaje;

    private Long usuarioId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public Tweet() {
    }

    public Tweet(Long id, String mensaje, Long usuarioId) {
        this.id = id;
        this.mensaje = mensaje;
        this.usuarioId = usuarioId;
    }

    /**
     * Get the value of mensaje
     *
     * @return the value of mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Set the value of mensaje
     *
     * @param mensaje new value of mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

}
