/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplosoapfaultclient;

import com.curso.ejemplosoapfault.ServicioExcepcion;
import com.curso.ejemplosoapfault.ServicioFault;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Principal {

    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String parametro = "correcto";
        llamadaCorrecta(parametro);
        parametro = "fault";
        llamadaErronea(parametro);

    }

    private static void llamadaErronea(String parametro) {
        try { // Call Web Service Operation
            com.curso.ejemplosoapfault.SoapFaultWs_Service service = new com.curso.ejemplosoapfault.SoapFaultWs_Service();
            com.curso.ejemplosoapfault.SoapFaultWs port = service.getSoapFaultWsPort();
            // TODO initialize WS operation arguments here
            java.lang.String nombre = parametro;
            // TODO process result here
            java.lang.String result = port.saludo(nombre);
            System.out.println("Result = " + result);
        } catch (ServicioExcepcion ex) {
            LOG.log(Level.SEVERE, "Error desde el servicio Web", ex);
            ServicioFault faultInfo = ex.getFaultInfo();
            LOG.log(Level.SEVERE, "C\u00f3digo: {0}.Mensaje: {1}", new Object[]{faultInfo.getFaultCode(), faultInfo.getFaultString()});
        }
    }

    private static void llamadaCorrecta(String parametro) {
        try { // Call Web Service Operation
            com.curso.ejemplosoapfault.SoapFaultWs_Service service = new com.curso.ejemplosoapfault.SoapFaultWs_Service();
            com.curso.ejemplosoapfault.SoapFaultWs port = service.getSoapFaultWsPort();
            // TODO initialize WS operation arguments here
            java.lang.String nombre = parametro;
            // TODO process result here
            java.lang.String result = port.saludo(nombre);
            System.out.println("Resultado = " + result);
        } catch (ServicioExcepcion ex) {
            LOG.log(Level.SEVERE, "Error desde el servicio Web", ex);
        }
    }

}
