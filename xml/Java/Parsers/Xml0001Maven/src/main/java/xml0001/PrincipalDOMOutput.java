/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml0001;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom.input.DOMBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author user
 */
public class PrincipalDOMOutput {

    public static void main(String[] args) {
        try {
            //Obtener un documento DOM nuevo
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = factoria.newDocumentBuilder();
            Document documento = constructor.newDocument();
            //Crear los elementos del documento y ensamblarlos
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
            //Mostrar el documentp por consola, recurriendo por comodidad a JDOM
            DOMBuilder builder = new DOMBuilder();
            org.jdom.Document doc = builder.build(documento);
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            out.output(doc, System.out);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalDOMOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PrincipalDOMOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
