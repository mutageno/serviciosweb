package com.curso.spring.model;

import java.io.Serializable;

public class Tienda implements Serializable {

    private String nombre;

    private String comerciales[];

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[] getComerciales() {
        return comerciales;
    }

    public void setComerciales(String[] comerciales) {
        this.comerciales = comerciales;
    }
}
