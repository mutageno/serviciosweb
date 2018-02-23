package sax.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ExtraerTitulosSAX {

    private List<String> titulos;
    private Boolean enTitulo = false;
    private Integer contador = 0;

    private class ManejadorImpl extends DefaultHandler {

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            if (enTitulo) {
                String texto = new String(ch, start, length);
                getTitulos().add(texto);
                System.out.format(
                        "El texto \"%s\" se ha guardado en la lista de títulos.%n",
                        texto);
            }
        }

        @Override
        public void endElement(String uri, String localName, String name)
                throws SAXException {
            if (name.equals("title")) {
                System.out.println("Saliendo de la etiqueta <title>.");
                enTitulo = false;
            }
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            System.out.println("Error: " + e.getMessage());
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            System.out.println("Error fatal: " + e.getMessage());
        }

        @Override
        public void startElement(String uri, String localName, String name,
                Attributes attributes) throws SAXException {
            if (name.equals("title")) {
                System.out.format(
                        "Entrando en la etiqueta <title> número %d.%n",
                        ++contador);
                enTitulo = true;
            }
        }

        @Override
        public void warning(SAXParseException e) throws SAXException {
            System.out.println("Warning: " + e.getMessage());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExtraerTitulosSAX e = new ExtraerTitulosSAX();
        e.analizarDocumento("/recursos/catalog.xml");
        e.mostrarTitulos();
    }

    private void mostrarTitulos() {
        System.out.format("Se han encontrado un total de %d título(s).%n",
                contador);
        for (String titulo : getTitulos()) {
            System.out.format("El título es \"%s\"%n", titulo);
        }
    }

    private void analizarDocumento(String archivo) {
        try {
            SAXParserFactory factoria = SAXParserFactory.newInstance();
            SAXParser analizador = factoria.newSAXParser();
            ManejadorImpl m = new ManejadorImpl();
            analizador.parse(ExtraerTitulosSAX.class.getResourceAsStream(archivo), m);
        } catch (IOException ex) {
            Logger.getLogger(ExtraerTitulosSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ExtraerTitulosSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ExtraerTitulosSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getTitulos() {
        if (titulos == null) {
            titulos = new ArrayList<String>();
        }
        return titulos;
    }
}
