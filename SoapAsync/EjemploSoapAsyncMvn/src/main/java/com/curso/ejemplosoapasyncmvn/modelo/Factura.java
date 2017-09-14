/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplosoapasyncmvn.modelo;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Factura implements Serializable {

    private Long id;

    private Double importe;

    public static final Factura ERRORES = new Factura(-1L, -1 - 0d);

    public Factura() {
    }

    public Factura(Long id, Double importe) {
        this.id = id;
        this.importe = importe;
    }

    /**
     * Get the value of importe
     *
     * @return the value of importe
     */
    public Double getImporte() {
        return importe;
    }

    /**
     * Set the value of importe
     *
     * @param importe new value of importe
     */
    public void setImporte(Double importe) {
        this.importe = importe;
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

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", importe=" + importe + '}';
    }

}
