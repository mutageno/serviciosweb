package com.curso.ejemplovalidacion.dom;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DOMValidator {

    public void validateSchema(String esquema, String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //Activar validación
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            
            //Configurar atributos
            factory.setAttribute(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            factory.setAttribute(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource",
                    esquema);
            
            DocumentBuilder builder = factory.newDocumentBuilder();

            Validator manejador = new Validator();
            builder.setErrorHandler(manejador);
            
            builder.parse(xml);
            
            if (manejador.errorDeValidacion) {
                System.out.println("Errores:" + manejador.errorDeValidacion + " " + manejador.saxParseException.getMessage());
            } else {
                System.out.println("El documento es válido");
            }
        } catch (java.io.IOException ioe) {
            System.out.println("IOException " + ioe.getMessage());
        } catch (SAXException e) {
            System.out.println("SAXException" + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException " + e.getMessage());
        }
    }
    //ErrorHandler Class
    private class Validator extends DefaultHandler {

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

    public static void main(String[] argv) {
        String esquema = "http://localhost:8080/EsquemaServerMaven/Chapter3/catalog.xsd";
        String xml = "http://localhost:8080/EsquemaServerMaven/Chapter3/catalog.xml";
        DOMValidator validador = new DOMValidator();
        validador.validateSchema(esquema, xml);
    }
}

