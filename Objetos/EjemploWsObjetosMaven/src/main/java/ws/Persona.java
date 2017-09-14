/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.Serializable;

/**
 *
 * @author jose maria
 */
public class Persona implements Serializable{

    private String nombre;

    public Persona() {
        nombre = "Sin nombre " + System.currentTimeMillis();
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
