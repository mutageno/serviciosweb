package dom4j.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ExtraerTitulosDom4j {

    private static final String SALIDA4_HTML = "salida4.html";
    private static final String HTML_XSLT = "/recursos/htmltrans.xslt";
    private static final String SALIDA3_XML = "salida3.xml";
    private static final String SALIDA2_XML = "salida2.xml";
    private static final String SALIDA1_XML = "salida1.xml";

    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            ExtraerTitulosDom4j e = new ExtraerTitulosDom4j();
            Document doc = e.analizarDocumento("/recursos/catalog.xml");
            List<Node> lista = e.extraerTitulos(doc);
            e.generarXmlDeSalida(lista);
            e.generarXmlDeSalidaConFormato(lista);
            e.transformarDocumentoConXsl(doc, HTML_XSLT);
            System.out.format("Por favor, inspeccione los archivos \"%s\", \"%s\", \"%s\" y \"%s\".%n", SALIDA1_XML, SALIDA2_XML, SALIDA3_XML, SALIDA4_HTML);
        } catch (TransformerException ex) {
            Logger.getLogger(ExtraerTitulosDom4j.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExtraerTitulosDom4j.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ExtraerTitulosDom4j.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void transformarDocumentoConXsl(Document doc, String hojaDeEstilo)
            throws TransformerException, IOException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(ExtraerTitulosDom4j.class.getResourceAsStream(hojaDeEstilo)));

        DocumentSource origen = new DocumentSource(doc);
        DocumentResult resultado = new DocumentResult();
        transformer.transform(origen, resultado);

        Document tDoc = resultado.getDocument();
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter(SALIDA4_HTML), format);
        writer.write(tDoc);
        writer.close();

    }

    private void generarXmlDeSalidaConFormato(List<Node> lista)
            throws IOException {
        Document doc = DocumentHelper.createDocument();
        Element raiz = doc.addElement("titulos");
        for (Node node : lista) {
            raiz.addElement("titulo").addText(node.getText());
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter(SALIDA2_XML), format);
        writer.write(doc);
        writer.close();

        format = OutputFormat.createCompactFormat();
        writer = new XMLWriter(new FileWriter(SALIDA3_XML), format);
        writer.write(doc);
        writer.close();

    }

    private void generarXmlDeSalida(List<Node> lista) throws IOException {
        Document doc = DocumentHelper.createDocument();
        Element raiz = doc.addElement("titulos");
        for (Node node : lista) {
            raiz.addElement("titulo").addText(node.getText());
        }
        FileWriter archivo = new FileWriter(SALIDA1_XML);
        doc.write(archivo);
        System.out.println("El texto XML del nuevo documento es: " + doc.asXML());
        archivo.close();
    }

    @SuppressWarnings("unchecked")
    private List<Node> extraerTitulos(Document doc) {
        List<Node> lista = doc.selectNodes("/catalog/journal/article/title");
        for (Node node : lista) {
            System.out.format("El título es \"%s\".%n", node.getText());
        }
        return lista;
    }

    private Document analizarDocumento(String archivo) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(ExtraerTitulosDom4j.class.getResourceAsStream(archivo));
        return doc;
    }
}
