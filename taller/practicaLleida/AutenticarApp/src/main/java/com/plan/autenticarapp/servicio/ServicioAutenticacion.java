/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plan.autenticarapp.servicio;

import com.plan.autenticarapp.dao.Autenticador;
import com.plan.autenticarapp.modelo.Usuario;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author khepherer
 */
@WebService(serviceName = "ServicioAutenticacion")
public class ServicioAutenticacion {

    @EJB
    private Autenticador autenticador;

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param nombre
     * @param clave
     * @return 
     */
    @WebMethod(operationName = "nuevoUsuario")
    public Usuario nuevoUsuario(@WebParam(name = "nombre") final String nombre, @WebParam(name = "clave") final String clave) {
        //TODO write your implementation code here:
        return autenticador.nuevo(nombre, clave);
    }
    
    @WebMethod(operationName = "autenticarPorNombreYClave")
    public Usuario autenticarPorNombreYClave(@WebParam(name = "nombre") final String nombre, @WebParam(name = "clave") final String clave) {
        //TODO write your implementation code here:
        return autenticador.autenticarPorNombreYClave(nombre, clave);
    }
}
