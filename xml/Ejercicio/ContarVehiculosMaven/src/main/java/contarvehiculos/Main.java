/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contarvehiculos;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author user
 */
public class Main {

    private static final String ARCHIVOXML = "concesionario.xml";
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static class ManejadorSAX extends DefaultHandler {

        private Integer numeroVehiculos = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("coche") || qName.equals("moto")) {
                numeroVehiculos++;
            }
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("Número de vehículos:" + numeroVehiculos);
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            System.out.format("Error:%s, en línea %d, columna %d.%n", e.getMessage(), e.getLineNumber(), e.getColumnNumber());
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            System.out.format("Error fatal:%s, en línea %d, columna %d.%n", e.getMessage(), e.getLineNumber(), e.getColumnNumber());
        }

        @Override
        public void warning(SAXParseException e) throws SAXException {
            System.out.format("Atención:%s, en línea %d, columna %d.%n", e.getMessage(), e.getLineNumber(), e.getColumnNumber());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Long comienzo = System.currentTimeMillis();
            InputStream is = Main.class.getResourceAsStream("/recursos/" + ARCHIVOXML);
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser parser = factoria.newSAXParser();
            parser.parse(is, new ManejadorSAX());
            Long fin = System.currentTimeMillis();
            System.out.println("Tiempo empleado en milisegundos:" + (fin - comienzo));
        } catch (SAXException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
