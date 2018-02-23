/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import static java.lang.System.out;
import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author iconotc
 */
public class ManejadorSaxTelefonos extends DefaultHandler {

    private Boolean enTelefono = false;
    private final List<String> telefonos = new LinkedList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("telefono")) {
            enTelefono = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (enTelefono) {
            telefonos.add(new String(ch, start, length));
            enTelefono = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        telefonos.forEach(out::println);
    }

}
