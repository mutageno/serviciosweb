/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.microservicios0001.rest;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author usuario
 */
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String nombre;
    private String correo;

    public Cliente() {
    }

    public Cliente(String name, String email) {       
        this.nombre = name;
        this.correo = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNome(String name) {
        this.nombre = name;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
