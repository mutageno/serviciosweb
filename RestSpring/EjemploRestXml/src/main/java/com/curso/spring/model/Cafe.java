package com.curso.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cafe")
public class Cafe {

    private String nombre;
    private int cantidad;

    public String getNombre() {
        return nombre;
    }

    @XmlElement
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    @XmlElement
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cafe(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Cafe() {
    }
}