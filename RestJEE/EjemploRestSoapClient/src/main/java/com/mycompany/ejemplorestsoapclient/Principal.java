/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplorestsoapclient;

import com.mycompany.ejemplorestsoapclient.cliente.NewJerseyClient;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewJerseyClient cliente = new NewJerseyClient();
        Usuario json = cliente.getJson(Usuario.class);
        System.out.println("Respuesta del servicio web: " + json);
        cliente.putJson(new Usuario(2000L,"ggggg"));
    }
    
}
