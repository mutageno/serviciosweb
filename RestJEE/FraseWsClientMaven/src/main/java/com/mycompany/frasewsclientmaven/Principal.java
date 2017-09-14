/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frasewsclientmaven;

import client.NewJerseyClient;

/**
 *
 * @author khepherer
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewJerseyClient c = new NewJerseyClient();
        String json = c.getJson(String.class);
        System.out.println(json);
        c.putJson("{\"nombre\":\"abc\"}");
    }
    
}
