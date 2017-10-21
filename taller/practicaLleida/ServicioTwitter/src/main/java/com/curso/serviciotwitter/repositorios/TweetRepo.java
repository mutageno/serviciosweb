/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.serviciotwitter.repositorios;

import com.curso.serviciotwitter.entidades.Tweet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author usuario
 */
public interface TweetRepo extends JpaRepository<Tweet, Long> {

    List<Tweet> findByUsuarioId(Long id);
}
