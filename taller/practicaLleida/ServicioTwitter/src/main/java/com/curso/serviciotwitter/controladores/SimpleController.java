/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.serviciotwitter.controladores;

import com.curso.serviciotwitter.entidades.Tweet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.curso.serviciotwitter.repositorios.TweetRepo;
import java.util.Arrays;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author usuario
 */
@RestController
public class SimpleController {

    private final TweetRepo repo;

    @Autowired
    public SimpleController(TweetRepo repo) {
        this.repo = repo;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/nuevo")
    public Tweet tweet(@RequestBody Tweet n) {
        repo.save(n);
        return new Tweet(n.getId(), n.getMensaje().toUpperCase(), n.getUsuarioId());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/tweets")
    public List<Tweet> todos() {
        return repo.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/tweets/{id}")
    public List<Tweet> tweet(@PathVariable("id") Long id) {
        Objects.requireNonNull(id);
        return Optional.ofNullable(repo.findByUsuarioId(id)).orElse(Arrays.asList(new Tweet(0L, "No existe", 0L)));
    }
}
