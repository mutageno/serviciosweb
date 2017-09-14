/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplorestsoap.utilidades;

import com.mycompany.ejemplorestsoap.soap.Persona;
import com.mycompany.ejemplorestsoap.soap.PersonaWebService;
import com.mycompany.ejemplorestsoap.soap.PersonaWebServiceService;

/**
 *
 * @author usuario
 */
public class GestorSoap {
    
    public Persona llamadaSoap() {
        Persona result = null;
        try { // Call Web Service Operation
            PersonaWebServiceService service = new PersonaWebServiceService();
            PersonaWebService port = service.getPersonaWebServicePort();
            // TODO process result here
            result = port.crearPersona();
            result.setNombre("Nombre añadido en el servicio Web Restful");
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error: " + ex);
        }
        return result;
    }
}
