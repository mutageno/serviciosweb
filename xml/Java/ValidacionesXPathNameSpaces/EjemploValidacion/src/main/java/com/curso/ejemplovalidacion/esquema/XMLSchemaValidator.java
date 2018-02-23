package com.curso.ejemplovalidacion.esquema;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;
import java.net.URL;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

public class XMLSchemaValidator {

    public void validateXMLDocument(URL esquema, URL xml) {
        try {      
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            
            Schema schema = factory.newSchema(esquema);
           
            Validator validador = schema.newValidator();
            ErrorHandlerImpl errorHandler = new ErrorHandlerImpl();
            validador.setErrorHandler(errorHandler);
            //Validar
            StreamSource streamSource = new StreamSource(xml.openStream());
            validador.validate(streamSource);
            
            if (errorHandler.errorDeValidacion) {
                System.out.println("Errores:" + errorHandler.errorDeValidacion + " " + errorHandler.saxParseException.getMessage());
            } else {
                System.out.println("El documento es válido");
            }
        } catch (SAXException e) {
            Logger.getLogger(XMLSchemaValidator.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(XMLSchemaValidator.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] argv) {
        try {
            URL esquema = new URL("http://localhost:8080/EsquemaServerMaven/Chapter3/catalog.xsd");
            URL xml = new URL("http://localhost:8080/EsquemaServerMaven/Chapter3/catalog.xml");
            XMLSchemaValidator validador = new XMLSchemaValidator();
            validador.validateXMLDocument(esquema, xml);
        } catch (MalformedURLException ex) {
            Logger.getLogger(XMLSchemaValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class ErrorHandlerImpl extends DefaultHandler {

        public boolean errorDeValidacion = false;
        public SAXParseException saxParseException = null;

        @Override
        public void error(SAXParseException exception) throws SAXException {
            errorDeValidacion = true;
            saxParseException = exception;
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            errorDeValidacion = true;
            saxParseException = exception;
        }

        @Override
        public void warning(SAXParseException exception) throws SAXException {
        }
    }
}

