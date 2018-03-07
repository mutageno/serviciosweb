/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.jaxwshandlerserver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

/**
 *
 * @author usuario
 */
public class MacAddressValidatorHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOG = Logger.getLogger(MacAddressValidatorHandler.class.getName());
    
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("Server : handleMessage()......");
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        //Solo para mensajes de respuesta, true para mensajes outbound, false para inbound
        if (!isRequest) {
            try {
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();
                //Obtener la dirección MAC de la cabecera SOAP
                Iterator it = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
                //Si no hay bloque de cabecera para el siguiente actor lanzar excepción
                if (it == null || !it.hasNext()) {
                    generateSOAPErrMessage(soapMsg, "No se encuentra el bloque para el siguiente actor en la cabecera.");
                }
                //La dirección MAC no se encuentra. Lanzar excepción
                Node macNode = (Node) it.next();
                String macValue = (macNode == null) ? null : macNode.getValue();
                if (macValue == null) {
                    generateSOAPErrMessage(soapMsg, "No se encuentra la dirección MAC.");
                }
                //La dirección MAC no es válida.Lanzar excepción
                if (!macValue.equals("0A-00-27-00-00-0D")) {
                    generateSOAPErrMessage(soapMsg, "La dirección MAC no es válida. Acceso denegado.");
                }
                //Escribir una traza del mensaje SOAP
                soapMsg.writeTo(System.out);
            } catch (SOAPException | IOException e) {
                LOG.log(Level.SEVERE, "Ha ocurrido un error", e);
            }
        }
        //Permitir que la cadena de handlers prosiga, por si hay alguno más
        return true;
    }
    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("Server : handleFault()......");
        return true;
    }
    @Override
    public void close(MessageContext context) {
        System.out.println("Server : close()......");
    }
    @Override
    public Set<QName> getHeaders() {
        System.out.println("Server : getHeaders()......");
        return null;
    }
    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new SOAPFaultException(soapFault);
        } catch (SOAPException e) {
            LOG.log(Level.SEVERE, "Ha ocurrido un error al generar la SOAP Fault", e);
        }
    }
}
