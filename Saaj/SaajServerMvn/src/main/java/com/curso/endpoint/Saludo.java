/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author usuario
 */
@WebService
public interface Saludo {

    /**
     *
     * @param texto
     * @return
     */
    @WebMethod
    public String getSaludoMsg(String texto);
}
