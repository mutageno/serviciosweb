/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.cliente;

import com.curso.endpoint.Saludo;
import com.curso.endpoint.SaludoImplService;

/**
 *
 * @author usuario
 */
public class SaludoMsgClient {

    public static void main(String[] args) {
        SaludoImplService saludoService = new SaludoImplService();
        Saludo saludo = saludoService.getSaludoImplPort();
        System.out.println(saludo.getSaludoMsg("abc"));
    }

}
