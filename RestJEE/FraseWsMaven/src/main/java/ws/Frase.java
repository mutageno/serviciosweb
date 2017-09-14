/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

/**
 *
 * @author Chema
 */
public class Frase {
    
    private String contenido;

    @Override
    public String toString() {
        return "Frase{" + "contenido=" + contenido + '}';
    }

    public Frase(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Get the value of contenido
     *
     * @return the value of contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Set the value of contenido
     *
     * @param contenido new value of contenido
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
