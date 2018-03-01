/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplosoapfault;

import javax.xml.ws.WebFault;

/**
 *
 * @author usuario
 */
@WebFault(name = "ServicioFault", targetNamespace = "http://ejemplosoapfault.curso.com/")
public class ServicioExcepcion extends Exception {

    private ServicioFault fault = new ServicioFault();

    public ServicioExcepcion() {
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * @param fault
     */
    protected ServicioExcepcion(ServicioFault fault) {
        super(fault.getFaultString());
        this.fault = fault;
    }

    /**
     *
     * @param message
     * @param faultInfo
     */
    public ServicioExcepcion(String message, ServicioFault faultInfo) {
        super(message);
        this.fault = faultInfo;
    }

    /**
     *
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ServicioExcepcion(String message, ServicioFault faultInfo, Throwable cause) {
        super(message, cause);
        this.fault = faultInfo;
    }

    /**
     *
     * @return
     */
    public ServicioFault getFaultInfo() {
        return fault;
    }

    /**
     * @param message
     */
    public ServicioExcepcion(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param code
     * @param message
     */
    public ServicioExcepcion(String code, String message) {
        super(message);
        this.fault.setFaultString(message);
        this.fault.setFaultCode(code);
    }

    /**
     * @param cause
     */
    public ServicioExcepcion(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public ServicioExcepcion(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}
