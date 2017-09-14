/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.microservicios0001.repos;

import com.curso.microservicios0001.rest.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author usuario
 */
public interface ClienteRepo extends JpaRepository<Cliente, Long>{
    
}
