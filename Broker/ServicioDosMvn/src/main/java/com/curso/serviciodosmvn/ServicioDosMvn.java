/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.serviciodosmvn;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "ServicioDosMvn")
public class ServicioDosMvn {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "servicioDosMensaje")
    public String hello(@WebParam(name = "name") String txt) {
        return "Servicio dos " + txt + " !";
    }
}
