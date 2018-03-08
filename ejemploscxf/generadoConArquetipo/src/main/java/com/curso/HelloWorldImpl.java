
package com.curso;

import javax.jws.WebService;

@WebService(endpointInterface = "com.curso.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

