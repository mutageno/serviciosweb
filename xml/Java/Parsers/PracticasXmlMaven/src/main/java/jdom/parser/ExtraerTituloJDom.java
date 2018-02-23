package jdom.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

public class ExtraerTituloJDom {

    private static final String SALIDAJDOM_XML = "salidajdom.xml";

    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            SAXBuilder constructor = new SAXBuilder();
            Document doc = constructor.build(ExtraerTituloJDom.class.getResourceAsStream("/recursos/catalog.xml"));
            List<Element> lista = XPath.selectNodes(doc, "/catalog/journal/article/title");
            Document salida = new Document();
            Element raiz = new Element("titulos");
            salida.setRootElement(raiz);
            for (Element elemento : lista) {
                System.out.format("El título es \"%s\".%n", elemento.getText());
                Element e = new Element("titulo");
                e.setText(elemento.getText());
                raiz.addContent(e);
            }
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            out.output(salida, new FileWriter(SALIDAJDOM_XML));
            System.out.format("Por favor, inspeccione el documento \"%s\".%n", SALIDAJDOM_XML);
        } catch (JDOMException ex) {
            Logger.getLogger(ExtraerTituloJDom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExtraerTituloJDom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
