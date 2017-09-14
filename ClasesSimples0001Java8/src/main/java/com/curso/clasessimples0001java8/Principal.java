/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.clasessimples0001java8;

import com.curso.beans.UsuarioInmutable;
import static com.curso.beans.UsuarioInmutable.crearUsuario;
import static com.curso.funcional.EjecutorTransaccional.run;
import static com.curso.funcional.EjecutorTransaccional.runEnParalelo;
import static com.curso.funcional.EjecutorTransaccional.runQuery;
import com.curso.funcional.QueryTransaccional;
import com.curso.utilidades.HibernateUtil;
import static java.lang.System.out;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.hibernate.Session;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        testSecuencial();
        testEnParalelo();
        HibernateUtil.close();
    }

    private static void testSecuencial() {
        UsuarioInmutable usuarioInmutable = crearUsuario("nombre uno", "clave dos");
        run((UsuarioInmutable usuario, Session sesion) -> {
            sesion.persist(usuario);
            return usuario;
        }, usuarioInmutable).ifPresent(out::println);

        run((u, s) -> {
            s.update(u);
            return u;
        }, usuarioInmutable.conNombre("Otro nombre")).ifPresent(out::println);

        runQuery((q, s) -> s.createQuery(q).list(), "from UsuarioInmutable").ifPresent(out::println);

        run((u, s) -> {
            s.delete(u);
            return u;
        }, usuarioInmutable);

        Optional<Collection<UsuarioInmutable>> p = runQuery(
                (q, s) -> s.getNamedQuery(q).list(),
                "UsuarioInmutable.findAll"
        );
        assert p.map(Collection::isEmpty).orElse(false);
    }

    private static void testEnParalelo() {

        run((usuario, sesion) -> {
            sesion.persist(usuario);
            return usuario;
        }, crearUsuario("z nombre", "z clave"));
        run((usuario, sesion) -> {
            sesion.persist(usuario);
            return usuario;
        }, crearUsuario("a nombre", "a clave"));

        Map<QueryTransaccional<UsuarioInmutable>, String> mapa = new HashMap<>();
        mapa.put((q, s) -> s.createQuery(q).list(), "from UsuarioInmutable u order by u.nombre");
        mapa.put((q, s) -> s.getNamedQuery(q).list(), "UsuarioInmutable.findAll");
        runEnParalelo(mapa);
    }
}
