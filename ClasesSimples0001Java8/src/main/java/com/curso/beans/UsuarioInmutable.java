/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Chema
 */
public class UsuarioInmutable implements Serializable {

    private static final long serialVersionUID = -1L;

    public static UsuarioInmutable crearUsuario(String nombre, String clave) {
        return new UsuarioInmutable(nombre, clave);
    }

    public UsuarioInmutable conNombre(String nombre) {
        return new UsuarioInmutable(id, nombre, clave);
    }

    public UsuarioInmutable conClave(String clave) {
        return new UsuarioInmutable(id, nombre, clave);
    }
    
    private Long id;
    private String nombre;
    private String clave;

    UsuarioInmutable() {
    }

    private UsuarioInmutable(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    private UsuarioInmutable(Long id, String nombre, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    private void setClave(String clave) {
        this.clave = clave;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.clave);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioInmutable other = (UsuarioInmutable) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.clave, other.clave);
    }

    @Override
    public String toString() {
        List<String> l = Arrays.asList("Usuario{", "id=", id.toString(), ", nombre=", nombre, ", clave=", clave, "}");
        return l.stream().reduce("", String::concat);
    }
}
