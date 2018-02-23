/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplotelefonos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author formacion
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Principal p = new Principal();
            List<String> telefonos = p.obtenerTelefonos("agenda.xml");
            Document d = p.construirNuevoArbol(telefonos);
            p.volcarNuevoArbol(d, System.out);
            p.volcarNuevoArbol(d, new FileOutputStream("nuevo.xml"));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<String> obtenerTelefonos(String archivoXml) throws ParserConfigurationException, SAXException, IOException {
        InputStream is = Principal.class.getResourceAsStream("/" + archivoXml);
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = factoria.newDocumentBuilder();
        Document documento = constructor.parse(is);
        Element e = documento.getDocumentElement();
        NodeList nodos = e.getElementsByTagName("telefono");
        List<String> r = new ArrayList<>();
        for (int i = 0; i < nodos.getLength(); i++) {
            Node n = nodos.item(i);
            r.add(n.getFirstChild().getNodeValue());
        }
        return r;
    }

    private Document construirNuevoArbol(List<String> telefonos) throws ParserConfigurationException {
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = factoria.newDocumentBuilder();
        Document documento = constructor.newDocument();
        Element raiz = documento.createElement("telefonos");
        for (String telefono : telefonos) {
            Element nombre = documento.createElement("telefono");
            Text texto = documento.createTextNode(telefono);
            nombre.appendChild(texto);
            raiz.appendChild(nombre);
        }
        documento.appendChild(raiz);
        return documento;
    }

    private void volcarNuevoArbol(Document d, OutputStream out) throws IOException {
        DOMBuilder builder = new DOMBuilder();
        org.jdom.Document doc = builder.build(d);
        XMLOutputter salida = new XMLOutputter(Format.getPrettyFormat());
        salida.output(doc, out);
    }
}
