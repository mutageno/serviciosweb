/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientefraseasyncmaven;

import client.ClienteRestAsync;

/**
 *
 * @author usuario
 */
public class ClienteFraseWsAsync {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteRestAsync cliente = new ClienteRestAsync();
        Object respuestaJson = cliente.getJson(String.class);
        System.out.println(respuestaJson);
        cliente.putJson(respuestaJson);
        cliente.close();
    }

}
