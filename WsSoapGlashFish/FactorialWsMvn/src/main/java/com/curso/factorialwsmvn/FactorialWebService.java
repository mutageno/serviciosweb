/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.factorialwsmvn;

import java.util.stream.Stream;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author khepherer
 */
@WebService(serviceName = "FactorialWebService")
@Stateless
public class FactorialWebService {

    /**
     * Web service operation
     *
     * @param numero
     * @return el factorial de numero como Double
     */
    @WebMethod(operationName = "factorial")
    public Double factorial(@WebParam(name = "numero") final Double numero) {
        return calcFactorial(numero);
    }

    private Double calcFactorial(Double numero) {
        return Stream.iterate(1.0d, n -> n + 1).parallel()
                .limit(numero.longValue()).reduce(1.0d, (a, b) -> a * b);
    }
}
