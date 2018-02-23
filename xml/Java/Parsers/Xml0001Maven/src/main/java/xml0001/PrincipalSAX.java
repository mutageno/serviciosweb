/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml0001;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author user
 */
public class PrincipalSAX {

    private static final String ARCHIVOXML = "agenda.xml";
    private static final String ARCHIVODTD = "agenda.dtd";
    private static final Logger logger = Logger.getLogger(PrincipalSAX.class.getName());

    private static class ManejadorSAX extends DefaultHandler {

        private Locator locator;

        @Override
        public void setDocumentLocator(Locator locator) {
            System.out.println("Método setDocumentLocator llamado por el parser");
            this.locator = locator;
        }

        @Override
        public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
            if (systemId.endsWith(ARCHIVODTD)) {
                InputStream is = ManejadorSAX.class.getResourceAsStream("/recursos/" + ARCHIVODTD);
                return new InputSource(is);
            } else {
                return super.resolveEntity(publicId, systemId);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("Fin del documento");
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Comienzo del documento");
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.format("Fin del elemento. Uri:%s. Nombre local:%s. Nombre cualificado:%s.%n", uri, localName, qName);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.format("Comienzo del elemento. Uri:%s. Nombre local:%s. Nombre cualificado:%s.Tiene atributos:%s%n", uri, localName, qName, attributes.getLength() == 0 ? "No" : "Sí");
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.format("Nombre:%s. Valor:%s.%n", attributes.getQName(i), attributes.getValue(i));
            }
            if (locator != null) {
                int col = locator.getColumnNumber();
                int line = locator.getLineNumber();
                String publicId = locator.getPublicId();
                String systemId = locator.getSystemId();
                System.out.println("En locator");
            }

        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {
            System.out.format("Instrucción de procesamiento. Target:%s. Data:%s.%n", target, data);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length);
            System.out.format("Encontrado texto:%s.%n", str);
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
            InputStream is = PrincipalSAX.class.getResourceAsStream("/recursos/" + ARCHIVOXML);
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser parser = factoria.newSAXParser();
            parser.parse(is, new ManejadorSAX());
            System.out.println("Fin");
        } catch (SAXException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
