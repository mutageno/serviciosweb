/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author iconotc
 */
public class ManejadorSax extends DefaultHandler {

    private Integer contador = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes a) throws SAXException {
        if (qName.equalsIgnoreCase("contacto")
                && a.getValue("clase") != null
                && a.getValue("clase").equals("personal")) {
            contador++;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Contactos " + contador);
    }

}
