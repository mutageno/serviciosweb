/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Node;

/**
 *
 * @author usuario
 */
public class EjemploMessageHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOG = Logger.getLogger(EjemploMessageHandler.class.getName());

    @Override
    public boolean handleMessage(SOAPMessageContext contexto) {
        try {
            final SOAPMessage mensaje = contexto.getMessage();
            final SOAPBody cuerpo = mensaje.getSOAPBody();
            final String nombreLocal = cuerpo.getFirstChild().getLocalName();
            if ("fechaResponse".equals(nombreLocal)) {
                final Node nodoResponse = cuerpo.getFirstChild();
                final Node nodoReturn = nodoResponse.getFirstChild();
                final Node nodoFecha = nodoReturn.getFirstChild();

                LOG.log(Level.INFO, "Nombre local: {0}. Fecha: {1}", new Object[]{nombreLocal, nodoFecha.getNodeValue()});
            }
            return true;
        } catch (SOAPException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

}
