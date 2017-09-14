/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.ejemplorequestbody.controladores;

import com.curso.ejemplorequestbody.entidades.Noticia;
import com.curso.ejemplorequestbody.repositorios.NoticiaRepo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
public class SimpleController {

    private final NoticiaRepo repo;

    @Autowired
    public SimpleController(NoticiaRepo repo) {
        this.repo = repo;
    }

    @RequestMapping("/mensaje")
    public String menssje() {
        return "Fecha y hora " + new Date();
    }

    @RequestMapping("/descripcion")
    public Noticia noticia(@RequestBody Noticia n) {
        repo.save(n);
        return new Noticia(n.getId(), n.getTitulo().toUpperCase(), n.getCuerpo().toUpperCase());
    }

    @RequestMapping("/descripcionentity")
    public ResponseEntity<Noticia> noticia1(@RequestBody Noticia n) {
        repo.save(n);
        Noticia n1 = new Noticia(n.getId(), n.getTitulo().toUpperCase(), n.getCuerpo().toUpperCase());
        return new ResponseEntity<>(n1, HttpStatus.OK);
    }

    @RequestMapping("/noticias")
    public List<Noticia> todas() {
        return repo.findAll();
    }
}
