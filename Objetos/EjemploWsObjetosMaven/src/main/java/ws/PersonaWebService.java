/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;

/**
 *
 * @author usuario
 */
@WebService
public class PersonaWebService {

    public Persona crearPersona() {
        return new Persona();
    }
}
