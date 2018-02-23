/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conversiondom;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.jdom.input.DOMBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Generando el archivo xml de salida en salida.xml...");
            generarDocumentoXmlDeSalida();
            System.out.println("Generando la nueva página html en salida.html...");
            realizarTransformacionXmlHtml();
            System.out.println("Mostrando la nueva página html en el navegador...");
            mostrarPaginaHtml();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DOMException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void generarDocumentoXmlDeSalida() throws DOMException, ParserConfigurationException, SAXException, IOException {
        List<String> telefonos = new ArrayList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(Main.class.getResourceAsStream("/recursos/agenda.xml"));
        System.out.println("El documento se ha analizado sin errores");
        System.out.println("Buscando todos los teléfonos");
        NodeList nodeList = document.getElementsByTagName("telefono");
        for (Integer i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String telefono = node.getFirstChild().getNodeValue();
            telefonos.add(telefono);
        }
        System.out.format("Encontrados %d teléfonos%n", telefonos.size());
        System.out.println("Construyendo el documento xml de salida");
        Document salida = builder.newDocument();
        Element raiz = salida.createElement("telefonos");
        for (String telefono : telefonos) {
            Element tmp = salida.createElement("telefono");
            tmp.setAttribute("numero", telefono);
            raiz.appendChild(tmp);
        }
        salida.appendChild(raiz);
        DOMBuilder dOMBuilder = new DOMBuilder();
        org.jdom.Document doc = dOMBuilder.build(salida);
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        out.output(doc, System.out);
        FileWriter fileWriter = new FileWriter("salida.xml");
        out.output(doc, fileWriter);       
        fileWriter.close();
    }

    private static void realizarTransformacionXmlHtml() throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        final String ARCHIVOXML = "salida.xml";
        final String ARCHIVOXSL = "/recursos/salida.xsl";
        //Obtención del árbol DOM
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = factoria.newDocumentBuilder();
        InputStream xml = new FileInputStream(ARCHIVOXML);
        Document documento = constructor.parse(xml);
        xml.close();
        //Transformación en un nuevo documento XML
        TransformerFactory factoriaDeTransformacion = TransformerFactory.newInstance();
        //Creación de la fuente de datos xsl
        InputStream xsl = Main.class.getResourceAsStream(ARCHIVOXSL);
        Source xslSource = new StreamSource(xsl);
        //Creación de la fuente de datos xml
        Source xmlSource = new DOMSource(documento);
        //Creación de un transformador
        Transformer tranformador = factoriaDeTransformacion.newTransformer(xslSource);
        //Creación del resultado
        FileWriter f = new FileWriter("salida.html");
        Result resultado = new StreamResult(f);
        //Realizar la transformación
        tranformador.transform(xmlSource, resultado);
        f.close();
    }

    private static void mostrarPaginaHtml() throws IOException {
        Runtime.getRuntime().exec("cmd /c salida.html");        
    }
}
