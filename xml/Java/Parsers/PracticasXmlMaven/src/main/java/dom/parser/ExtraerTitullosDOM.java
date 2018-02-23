package dom.parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.input.DOMBuilder;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class ExtraerTitullosDOM {

    private static final String SALIDA_XML = "titulos.xml";
    private List<String> titulos;
    private DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

    public List<String> getTitulos() {
        if (titulos == null) {
            titulos = new ArrayList<String>();
        }
        return titulos;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            ExtraerTitullosDOM e = new ExtraerTitullosDOM();
            e.analizarDocumento();
            e.generarXmlDeSalida();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ExtraerTitullosDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExtraerTitullosDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarXmlDeSalida() throws ParserConfigurationException,
            IOException {
        DocumentBuilder b = factoria.newDocumentBuilder();
        Document d = b.newDocument();
        Element raiz = d.createElement("titulos");
        d.appendChild(raiz);
        for (String titulo1 : getTitulos()) {
            System.out.format("El título es \"%s\"%n", titulo1);
            Element titulo = d.createElement("titulo");
            raiz.appendChild(titulo);
            Text texto = d.createTextNode(titulo1);
            titulo.appendChild(texto);
        }
        d.normalizeDocument();
        DOMBuilder builder = new DOMBuilder();
        org.jdom.Document doc = builder.build(d);
        XMLOutputter out = new XMLOutputter();
        out.output(doc, new FileOutputStream(SALIDA_XML));
        System.out.format(
                "Proceso finalizado.%nPor favor, inspeccione el archivo \"%s\"%n",
                SALIDA_XML);
    }

    private void analizarDocumento() {
        try {
            DocumentBuilder analizador;
            analizador = factoria.newDocumentBuilder();
            Document documento = analizador.parse(ExtraerTitullosDOM.class.getResourceAsStream("/recursos/catalog.xml"));
            NodeList nodosTitulo = documento.getElementsByTagName("title");
            for (int i = 0; i < nodosTitulo.getLength(); i++) {
                Node nodo = (Element) nodosTitulo.item(i);
                nodo = nodo.getFirstChild();
                getTitulos().add(nodo.getNodeValue());
            }
        } catch (SAXException ex) {
            Logger.getLogger(ExtraerTitullosDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExtraerTitullosDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ExtraerTitullosDOM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
