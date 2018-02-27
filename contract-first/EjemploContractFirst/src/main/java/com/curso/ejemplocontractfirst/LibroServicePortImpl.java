package com.curso.ejemplocontractfirst;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@WebService(endpointInterface = "com.curso.ejemplocontractfirst.LibroServicePortType")
public class LibroServicePortImpl implements LibroServicePortType {

    private static final Logger LOG = Logger.getLogger(LibroServicePortImpl.class.getName());

    @Override
    public LibroServiceResponseType obtenerLibros(
            final LibroServiceRequestType LibroServiceRequest) {
        final LibroServiceResponseType response = new LibroServiceResponseType();
        for (int i = 0; i < LibroServiceRequest.getLimite(); i++) {
            final LibroType libro = new LibroType();
            libro.setAutor("Acme " + i);
            try {
                libro.setFecha(DatatypeFactory.newInstance()
                        .newXMLGregorianCalendar(
                                new GregorianCalendar(2018, 3, 5)));
            } catch (DatatypeConfigurationException e) {
                LOG.log(Level.SEVERE, "Ha ocurrido un error", e);
            }
            libro.setTitulo("Servicios Web SOAP en Java #" + i);
            response.getLibro().add(libro);
        }
        return response;
    }

}
