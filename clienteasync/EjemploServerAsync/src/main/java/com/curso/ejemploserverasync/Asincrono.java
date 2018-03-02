/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemploserverasync;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "Asincrono")
public class Asincrono {

    /**
     * Web service operation
     * @param uno
     * @param dos
     * @return la suma de los dos parámetros
     */
    @WebMethod(operationName = "suma")
    public int suma(@WebParam(name = "uno") final int uno, @WebParam(name = "dos") final int dos) {
        //TODO write your implementation code here:
        return uno + dos;
    }
    
}
