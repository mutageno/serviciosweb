/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplosoapfault;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "SoapFaultWs")
public class SoapFaultWs {

    @WebMethod
    public String saludo(@WebParam(name = "nombre") String nombre) throws ServicioExcepcion {
        if (nombre.equalsIgnoreCase("fault")) {
            throw new ServicioExcepcion("1234", "Error personalizado");
        } else {
            return "Saludos " + nombre;
        }        
    }
}
