/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplowsclientemaven;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;
import lib.Constantes;
import lib.MensajeJaxb;

/**
 *
 * @author Chema
 */
public class ClienteMensajes {

    private static final Logger LOG = Logger.getLogger(ClienteMensajes.class.getName());

    public static void main(String[] as) throws Exception {

        Dispatch<Object> port = configurar();
        llamadaNormal(port, new MensajeJaxb("Mensaje 1"));
        llamadaOneWay(port, new MensajeJaxb("Mensaje 2"));
        llamadaAsincrona(port, new MensajeJaxb("Mensaje 4"));
        llamadaAsincronaConFuture(port, new MensajeJaxb("Mensaje 3"));
    }

    private static void llamadaNormal(Dispatch<Object> port, MensajeJaxb peticionNormal) {
        // síncrono
        System.out.println("1 - Normal - Comienzo: " + new Date());
        MensajeJaxb response = (MensajeJaxb) port.invoke(peticionNormal);
        System.out.println("1 - Normal - Fin: " + new Date()
                + " respuesta: " + response.getResultado());
    }

    private static void llamadaOneWay(Dispatch<Object> port, MensajeJaxb peticionOneWay) {
        // one Way - síncrono espera una respuesta HTTP
        System.out.println("2 - OneWay - Comienzo: " + new Date());
        port.invokeOneWay(peticionOneWay);
        System.out.println("2 - OneWay - sin respuesta Fin: " + new Date());
    }

    private static void llamadaAsincrona(Dispatch<Object> port, MensajeJaxb peticionResponse) throws InterruptedException, ExecutionException {
        // petición asíncrona
        System.out.println("3 - Asynchronous - Comienzo: " + new Date());
        Response<Object> responseASR = port.invokeAsync(peticionResponse);
        System.out.println("3 - Asynchronous - Fin: " + new Date());

        while (!responseASR.isDone()) {
            Thread.sleep(900);
            System.out.println(" - 3.1 simulando hilo principal");
        }

        MensajeJaxb res = (MensajeJaxb) responseASR.get();
        System.out.println(" - 3.2 Asynchronous petición respondida en: "
                + new Date() + " con Response: " + res.getResultado());
    }

    private static void llamadaAsincronaConFuture(Dispatch<Object> port, MensajeJaxb peticionFuture) throws InterruptedException, ExecutionException {
        // asíncrono con Future
        AsyncHandler<Object> responseHandler = new AsyncHandler<Object>() {
            @Override
            public void handleResponse(Response<Object> resp) {
                try {
                    MensajeJaxb result = (MensajeJaxb) resp.get();
                    System.out.println(" - Future respuesta asíncrona "
                            + result.getResultado());
                } catch (InterruptedException | ExecutionException e) {
                    LOG.log(Level.SEVERE, "Error", e);
                }
            }
        };

        System.out.println("4 - Asynchronous con Future Comienzo: " + new Date());
        Future<?> f = port.invokeAsync(peticionFuture, responseHandler);
        System.out.println("4 - Asynchronous con Future Fin: " + new Date());

        while (!f.isDone()) {
            Thread.sleep(900);
            System.out.println(" - 4.1 simulando hilo principal");
        }
        System.out.println("4 - Asynchronous petición con Future respondida en: "
                + new Date() + " con Response: " + f.get());
    }

    private static Dispatch<Object> configurar() throws JAXBException {
        // Configuración
        QName serviceName = new QName("urn:ejemplos", "Ejemplos");
        QName portName = new QName("urn:ejemplos", "Saludo");
        Service service = Service.create(serviceName);
        service.addPort(
                portName,
                SOAPBinding.SOAP11HTTP_BINDING,
                Constantes.url);
        JAXBContext jaxbCtx = JAXBContext.newInstance(MensajeJaxb.class);
        Dispatch<Object> port = service.createDispatch(
                portName,
                jaxbCtx,
                Mode.PAYLOAD);
        return port;
    }
}
