package com.curso;

import javax.jws.WebService;

@WebService(endpointInterface = "com.curso.Persona")
public class PersonaImpl implements Persona {

	@Override
	public int edad() {
		return 25;
	}

}
