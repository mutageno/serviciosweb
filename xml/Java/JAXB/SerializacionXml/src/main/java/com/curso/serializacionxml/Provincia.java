/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.serializacionxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose maria
 */
@XmlRootElement
public class Provincia {

    private String nombre;
    private List<Localidad> localidades = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }
}
