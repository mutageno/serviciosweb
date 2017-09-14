/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.endpoint;

import javax.jws.WebService;

/**
 *
 * @author usuario
 */
@WebService(endpointInterface = "com.curso.endpoint.Saludo")
public class SaludoImpl implements Saludo {

    @Override
    public String getSaludoMsg(String texto) {
        return "Bienvenido " + texto;
    }
}
