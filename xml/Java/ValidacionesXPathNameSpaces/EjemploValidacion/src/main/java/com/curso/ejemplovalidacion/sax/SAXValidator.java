package com.curso.ejemplovalidacion.sax;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.InputStream;
import java.net.URL;

public class SAXValidator {

    public void validateSchema(String esquema, InputStream input) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Acvtivar validación
            factory.setNamespaceAware(true);
            factory.setValidating(true);

            // Configurar características
            factory.setFeature("http://xml.org/sax/features/validation", true);
            factory.setFeature("http://apache.org/xml/features/validation/schema", true);
            factory.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);

            SAXParser parser = factory.newSAXParser();

            // Configurar atributos
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource",
                    esquema);

            Validator manejador = new Validator();

            parser.parse(input, manejador);

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
            System.out.println("ParserConfigurationException" + e.getMessage());
        }
    }

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
        try {
            String esquema = "http://localhost:8080/EsquemaServerMaven/Chapter3/catalog.xsd";
            String documento = "http://localhost:8080/EsquemaServerMaven/Chapter3/catalog.xml";
            URL url = new URL(documento);
            SAXValidator validador = new SAXValidator();
            validador.validateSchema(esquema, url.openStream());
        } catch (MalformedURLException ex) {
            Logger.getLogger(SAXValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SAXValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
