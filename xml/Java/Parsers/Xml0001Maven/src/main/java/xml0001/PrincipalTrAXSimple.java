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
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author user
 */
public class PrincipalTrAXSimple {

    private static final String ARCHIVOXML = "/recursos/agenda.xml";
    private static final String ARCHIVOXSL = "/recursos/nombresXml.xsl";
    private static final String ARCHIVODTD = "agenda.dtd";

    public static void main(String[] args) {
        try {
            //Obtención del árbol DOM
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
            InputStream xml = PrincipalTrAXSimple.class.getResourceAsStream(ARCHIVOXML);
            Document documento = constructor.parse(xml);
            //Transformación en un nuevo documento XML
            TransformerFactory factoriaDeTransformacion = TransformerFactory.newInstance();
            //Creación de la fuente de datos xsl
            InputStream xsl = PrincipalTrAXSimple.class.getResourceAsStream(ARCHIVOXSL);
            Source xslSource = new StreamSource(xsl);
            //Creación de la fuente de datos xml
            Source xmlSource = new DOMSource(documento);
            //Creación de un transformador
            Transformer tranformador = factoriaDeTransformacion.newTransformer(xslSource);
            //Creación del reultado
            Result resultado = new StreamResult(System.out);
            //Realizar la transformación
            tranformador.transform(xmlSource, resultado);            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(PrincipalTrAXSimple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(PrincipalTrAXSimple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PrincipalTrAXSimple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalTrAXSimple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PrincipalTrAXSimple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
