/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.clientebrokerwsmvn;

import javax.xml.ws.soap.SOAPFaultException;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(broker("uno", "contenido uno"));
        System.out.println(broker("dos", "contenido dos"));
        try {
            System.out.println(broker("tres", "contenido tres"));
        } catch (SOAPFaultException e) {
            System.out.println("Ha ocurrido un error: " + e);
        }
    }

    private static String broker(String servicio, String contenido) {
        com.curso.brokerwsmvn.BrokerWebServiceMvn_Service service = new com.curso.brokerwsmvn.BrokerWebServiceMvn_Service();
        com.curso.brokerwsmvn.BrokerWebServiceMvn port = service.getBrokerWebServiceMvnPort();
        return port.broker(servicio, contenido);

    }

}
