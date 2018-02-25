/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.jaxwshandlerserver;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "ServerInfo")
@HandlerChain(file="handler-chain.xml")
public class ServerInfo {

    @WebMethod
    public String getServerName() {
        return "Jax Ws handler server";
    }
}
