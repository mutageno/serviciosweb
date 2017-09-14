/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.clienteejemplowsobjetosmaven;

import ws.Persona;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Obteniendo un objeto desde un servicio Web...");
        Persona persona = crearPersona();
        System.out.println("El nombre es " + persona.getNombre());

    }

    private static Persona crearPersona() {
        Persona result = null;
        try { // Call Web Service Operation
            ws.PersonaWebServiceService service = new ws.PersonaWebServiceService();
            ws.PersonaWebService port = service.getPersonaWebServicePort();
            // TODO process result here
            result = port.crearPersona();
            System.out.println("Resultado = " + result);
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return result;
    }
}
