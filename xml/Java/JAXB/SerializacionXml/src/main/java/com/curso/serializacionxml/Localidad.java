/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.serializacionxml;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jose maria
 */
@XmlType
public class Localidad {

    private String nombre;
    private int codigoPostal;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
