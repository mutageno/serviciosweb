/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlconjava;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @autor jose maria
 */
public class Principal {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.newDocument();
            Element raiz = doc.createElement("autores");
            doc.appendChild(raiz);
            Element autor = doc.createElement("autor");
            autor.setAttribute("nombre", "Bashar AbdulJawad");
            Element libro = doc.createElement("libro");
            libro.setAttribute("titulo", "Groovy and Grails Recipes");
            libro.setAttribute("edicion", "1");
            autor.appendChild(libro);
            raiz.appendChild(autor);
            autor = doc.createElement("autor");
            autor.setAttribute("nombre", "Graeme Rocher");
            libro = doc.createElement("libro");
            libro.setAttribute("titulo", "The Definitive Guide to Grails");
            libro.setAttribute("edicion", "2");
            autor.appendChild(libro);
            raiz.appendChild(autor);
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(doc);
            Result dest = new StreamResult(System.out);
            aTransformer.transform(src, dest);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
