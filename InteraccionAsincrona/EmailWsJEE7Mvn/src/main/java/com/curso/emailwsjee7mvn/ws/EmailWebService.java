/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.emailwsjee7mvn.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "EmailWebService")
@Stateless()
public class EmailWebService {

    @Resource(mappedName = "jms/email")
    private Queue email;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    /**
     * This is a sample web service operation
     * @param txt
     * @return un mensaje para el cliente
     */
    @WebMethod(operationName = "email")
    public String email(@WebParam(name = "cuerpo") String txt) {
        sendJMSMessageToEmail(txt);
        return "Recibido " + txt + ". Ver el log del servidor";
    }

    private void sendJMSMessageToEmail(String messageData) {
        context.createProducer().send(email, messageData);
    }
}
