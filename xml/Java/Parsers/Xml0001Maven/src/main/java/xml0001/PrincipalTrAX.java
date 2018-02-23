/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml0001;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author user
 */
public class PrincipalTrAX {

    private static final String ARCHIVOXML1 = "nombres1.xml";
    private static final String ARCHIVOXML2 = "nombres2.xml";
    private static final String ARCHIVOXSL = "nombres.xsl";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = factoria.newDocumentBuilder();
            Document documento = constructor.newDocument();
            Element raiz = construirNuevoDocumento(documento);
            generarDocumentoDeSalida(raiz);
            generarDocumentoDeSalidaConFormato(raiz);
            generarDocumentoDeSalidaConFormatoEnMemoria(raiz);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalTrAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(PrincipalTrAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(PrincipalTrAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PrincipalTrAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Element construirNuevoDocumento(Document documento) throws DOMException {
        Element raiz = documento.createElement("nombres");
        Element nombre1 = documento.createElement("nombre");
        Element nombre2 = documento.createElement("nombre");
        Text texto1 = documento.createTextNode("Juan");
        Text texto2 = documento.createTextNode("Carlos");
        nombre1.appendChild(texto1);
        nombre2.appendChild(texto2);
        raiz.appendChild(nombre1);
        raiz.appendChild(nombre2);
        documento.appendChild(raiz);
        return raiz;
    }

    private static void generarDocumentoDeSalida(Element raiz) throws IOException, TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformador = factoria.newTransformer();
        Source domSource = new DOMSource(raiz);
        Result resultado = new StreamResult(new FileWriter(ARCHIVOXML1));
        transformador.transform(domSource, resultado);
        System.out.format("Por favor, inspeccione el archivo \"%s\".%n", ARCHIVOXML1);
    }

    private static void generarDocumentoDeSalidaConFormato(Element raiz) throws IOException, TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        InputStream is = PrincipalTrAX.class.getResourceAsStream("/recursos/" + ARCHIVOXSL);
        Source source = new StreamSource(is);
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformador = factoria.newTransformer(source);
        Source domSource = new DOMSource(raiz);
        Result resultado = new StreamResult(new FileWriter(ARCHIVOXML2));
        transformador.transform(domSource, resultado);
        System.out.format("Por favor, inspeccione el archivo \"%s\".%n", ARCHIVOXML2);
    }

    private static void generarDocumentoDeSalidaConFormatoEnMemoria(Element raiz) throws IOException, TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        InputStream is = PrincipalTrAX.class.getResourceAsStream("/recursos/" + ARCHIVOXSL);
        Source source = new StreamSource(is);
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformador = factoria.newTransformer(source);
        Source domSource = new DOMSource(raiz);
        StringWriter stringWriter = new StringWriter();
        Result resultado = new StreamResult(stringWriter);
        transformador.transform(domSource, resultado);
        System.out.format("El resultado es \"%s\".%n", stringWriter.toString());
    }
}
