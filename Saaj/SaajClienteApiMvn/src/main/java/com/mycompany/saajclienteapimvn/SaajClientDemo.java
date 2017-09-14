/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.saajclienteapimvn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author usuario
 */
public class SaajClientDemo {

    private static final Logger LOG = Logger.getLogger(SaajClientDemo.class.getName());

    public static Optional<String> formatearXml(final String input, final Integer indentacion) {
        Optional<String> resultado = Optional.empty();
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indentacion);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            resultado = Optional.of(xmlOutput.getWriter().toString());
        } catch (TransformerConfigurationException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    private static SOAPMessage crearSoapRequest() throws SOAPException, IOException, TransformerException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("end", "http://endpoint.curso.com/");
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement soapElement = soapBody.addChildElement("getSaludoMsg", "end");
        SOAPElement element1 = soapElement.addChildElement("arg0");
        element1.addTextNode("xyz");
        soapMessage.saveChanges();
        return soapMessage;
    }

    private static String crearSoapResponse(final SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(byteArray);
        transformer.transform(sourceContent, result);
        return byteArray.toString();
    }

    public static void main(String... args) {
        final String url = "http://localhost:8080/WebServiceDemo/Saludo?wsdl";
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = crearSoapRequest();

            LOG.log(Level.INFO, "----------SOAP Request------------");
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            soapRequest.writeTo(b);
            System.out.println(formatearXml(b.toString(), 2).orElse("Ha ocurrido un error en la request"));

            //Conectar cin el servidor y obtener respuesta
            SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
            String r = crearSoapResponse(soapResponse);
            LOG.log(Level.INFO, "\n----------SOAP Response-----------");
            System.out.println(formatearXml(r, 2).orElse("Ha ocurrido un error en la response"));

            soapConnection.close();
        } catch (SOAPException | UnsupportedOperationException | IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
