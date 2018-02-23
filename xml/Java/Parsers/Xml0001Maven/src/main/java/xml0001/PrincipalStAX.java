/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml0001;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author user
 */
public class PrincipalStAX {

    private static final String ARCHIVOXML = "agenda.xml";
    private static final Logger LOGGER = Logger.getLogger(PrincipalStAX.class.getName());

    public static void main(String[] args) {
        try {
            InputStream is = PrincipalStAX.class.getResourceAsStream("/recursos/" + ARCHIVOXML);
            XMLInputFactory factoria = XMLInputFactory.newInstance();
            XMLStreamReader lectorXml = factoria.createXMLStreamReader(is);
            while (lectorXml.hasNext()) {
                int evento = lectorXml.next();
                switch (evento) {
                    case XMLStreamConstants.START_DOCUMENT:
                        LOGGER.log(Level.INFO, "Comienzo del documento");
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        LOGGER.log(Level.INFO, "Fin del documento");
                        break;
                    case XMLStreamConstants.COMMENT:
                        LOGGER.log(Level.INFO, "Comentario");
                        break;
                    case XMLStreamConstants.PROCESSING_INSTRUCTION:
                        LOGGER.log(Level.INFO, "Instrucción de procesamiento");
                        System.out.format("Target:%s. Data:%s.%n", lectorXml.getPITarget(), lectorXml.getPIData());
                        break;
                    case XMLStreamConstants.DTD:
                        LOGGER.log(Level.INFO, "DTD");
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        String s = lectorXml.getLocalName();
                        LOGGER.log(Level.INFO, "Comienzo del elemento {0}", s);
                        if (s.equals("contacto")) {
                            for (int i = 0; i < lectorXml.getAttributeCount(); i++) {
                                System.out.format("Nombre del atributo:%s. Valor del atributo:%s.%n", lectorXml.getAttributeLocalName(i), lectorXml.getAttributeValue(i));
                            }
                        } else if (s.equals("nombre")) {
                            System.out.println("El nombre del contacto es " + lectorXml.getElementText());
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        LOGGER.log(Level.INFO, "Fin del elemento {0}", lectorXml.getLocalName());
                        break;
                }
            }
        } catch (XMLStreamException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
