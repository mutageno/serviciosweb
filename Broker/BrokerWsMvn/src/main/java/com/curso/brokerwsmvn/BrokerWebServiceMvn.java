/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.brokerwsmvn;

import com.curso.serviciodosmvn.ServicioDosMvn_Service;
import com.curso.serviciounomvn.ServicioUnoMvn_Service;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "BrokerWebServiceMvn")
public class BrokerWebServiceMvn {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ServicioDosMvn/ServicioDosMvn.wsdl")
    private ServicioDosMvn_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ServicioUnoMvn/ServicioUnoMvn.wsdl")
    private ServicioUnoMvn_Service service;

    @WebMethod(operationName = "broker")
    public String broker(@WebParam(name = "servicio") String servicio, @WebParam(name = "contenido") String contenido) {
        switch (servicio) {
            case "uno":
                return mensajeConServicioWebUno(contenido);
            case "dos":
                return mensajeConServicioWebDos(contenido);
            default:
                throw new WebServiceException("Servicio web desconocido");
        }        
    }
    private String mensajeConServicioWebUno(String texto) {
        try { // Call Web Service Operation
            com.curso.serviciounomvn.ServicioUnoMvn port = service.getServicioUnoMvnPort();
            return port.servicioUnoMensaje(texto);
        } catch (Exception ex) {
            return "No se ha podido acceder al servicio uno " + ex;
        }
    }

    private String mensajeConServicioWebDos(String texto) {
        try { // Call Web Service Operation
            com.curso.serviciodosmvn.ServicioDosMvn port = service_1.getServicioDosMvnPort();
            return port.servicioDosMensaje(texto);
        } catch (Exception ex) {
            return "No se ha podido acceder al servicio dos " + ex;
        }
    }
}
