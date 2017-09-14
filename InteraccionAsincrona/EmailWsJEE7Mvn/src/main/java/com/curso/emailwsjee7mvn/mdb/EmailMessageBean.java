/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.emailwsjee7mvn.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author usuario
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/email"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMessageBean implements MessageListener {

    private static final Logger LOG = Logger.getLogger(EmailMessageBean.class.getName());
    
    public EmailMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            String cuerpo = message.getBody(String.class);
            LOG.log(Level.INFO, "Recibido: {0}", cuerpo);
        } catch (JMSException ex) {
           LOG.log(Level.SEVERE, "Error", ex);
        }
        
    }
    
}
