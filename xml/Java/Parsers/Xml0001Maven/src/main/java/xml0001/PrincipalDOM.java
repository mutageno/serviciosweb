/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml0001;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 *
 * @author user
 */
public class PrincipalDOM {

    private static final String ARCHIVOXML = "agenda.xml";
    private static final String ARCHIVODTD = "agenda.dtd";
    private static final Logger logger = Logger.getLogger(PrincipalDOM.class.getName());

    public static void main(String[] args) {
        try {
            InputStream is = PrincipalDOM.class.getResourceAsStream("/recursos/" + ARCHIVOXML);
            Document documento = explorarDocumento(is);
            mostrarDetalles(documento);
            extraerNombres(documento);
        } catch (SAXException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private static Document explorarDocumento(InputStream is) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = factoria.newDocumentBuilder();
        constructor.setEntityResolver(new EntityResolver() {

            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                if (systemId.endsWith(ARCHIVODTD)) {
                    InputStream is = PrincipalDOM.class.getResourceAsStream("/recursos/" + ARCHIVODTD);
                    return new InputSource(is);
                } else {
                    return null;
                }
            }
        });
        constructor.setErrorHandler(new ErrorHandler() {

            public void warning(SAXParseException exception) throws SAXException {
                System.out.format("Atención:%s, en línea %d, columna %d.%n", exception.getMessage(), exception.getLineNumber(), exception.getColumnNumber());
            }

            public void error(SAXParseException exception) throws SAXException {
                System.out.format("Error:%s, en línea %d, columna %d.%n", exception.getMessage(), exception.getLineNumber(), exception.getColumnNumber());
            }

            public void fatalError(SAXParseException exception) throws SAXException {
            // Ignoramos este método. Cuando se produce un error fatal siempre se lanza una excepción.
            }
        });
        Document documento = constructor.parse(is);
        System.out.format("El documento \"%s\" se ha analizado correctamente.%n", ARCHIVOXML);
        System.out.println("Datos globales del documento:");
        String version = documento.getXmlVersion();
        String encoding = documento.getXmlEncoding();
        boolean standAlone = documento.getXmlStandalone();
        System.out.format("Version:%s. Encoding:%s. Standalone:%s.%n", version, encoding, standAlone);
        return documento;
    }

    private static void extraerNombres(Document documento) {
        Element e = documento.getDocumentElement();
        NodeList nodos = e.getElementsByTagName("nombre");
        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            System.out.format("Nombre del nodo:%s. Valor del nodo:%s%n", nodo.getNodeName(), nodo.getNodeValue());
            Node descendiente = nodo.getFirstChild();
            System.out.format("Nombre del nodo descendiente:%s. Valor del nodo:%s%n", descendiente.getNodeName(), descendiente.getNodeValue());
        }

    }

    private static void mostrarDetalles(Document documento) throws DOMException {
        Element e = documento.getDocumentElement();
        System.out.format("Elemento raíz del documento:%s.%n", e.getTagName());
        NodeList nodos = e.getChildNodes();
        System.out.format("El elemento \"%s\" tiene %d descendientes.%n", e.getTagName(), nodos.getLength());
        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            if (nodo.getNodeType() == Node.TEXT_NODE) {
                String contenido = nodo.getNodeValue().trim();
                System.out.println("Texto:" + contenido);
            }
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) nodo;
                System.out.println("Nombre:" + el.getTagName());
                if (el.hasAttributes()) {
                    NamedNodeMap atributos = el.getAttributes();
                    System.out.format("El elemento \"%s\" tiene %d atributo(s).%n", el.getTagName(), atributos.getLength());
                    for (int j = 0; j < atributos.getLength(); j++) {
                        Attr a = (Attr) atributos.item(j);
                        System.out.format("Nombre del atributo:%s. Contenido: %s.%n", a.getName(), a.getValue());
                    }
                }
                NodeList descendientes = el.getChildNodes();
                System.out.format("El elemento \"%s\" tiene %d descendientes.%n", el.getTagName(), descendientes.getLength());
            }
        }
    }
}
