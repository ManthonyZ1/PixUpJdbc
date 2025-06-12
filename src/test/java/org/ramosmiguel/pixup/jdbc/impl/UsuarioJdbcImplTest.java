package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.UsuarioJdbc;
import org.ramosmiguel.pixup.model.Usuario;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioJdbcImplTest {

    @Test
    void getInstance() {
        UsuarioJdbc dao = UsuarioJdbcImpl.getInstance();
        assertNotNull(dao);
    }

    @Test
    void findAll() {
        UsuarioJdbc dao = UsuarioJdbcImpl.getInstance();
        List<Usuario> usuarios = dao.findAll();
        assertNotNull(usuarios);
        assertTrue(usuarios.size() >= 0);
        usuarios.forEach(System.out::println);
    }

    @Test
    void save() {
        UsuarioJdbc dao = UsuarioJdbcImpl.getInstance();
        Usuario usuario = new Usuario();

        usuario.setId(100);
        usuario.setNombre("Mario");
        usuario.setPrimerApellido("Ramos");
        usuario.setSegundoApellido("González");
        usuario.setPassword("1234");
        usuario.setEmail("mario@example.com");

        boolean result = dao.save(usuario);
        assertTrue(result);
        System.out.println("Usuario guardado: " + usuario);
    }

    @Test
    void update() {
        UsuarioJdbc dao = UsuarioJdbcImpl.getInstance();
        Usuario usuario = new Usuario();

        usuario.setId(100);
        usuario.setNombre("Mario Modificado");
        usuario.setPrimerApellido("Ramos");
        usuario.setSegundoApellido("González");
        usuario.setPassword("abcd");
        usuario.setEmail("mario.modificado@example.com");

        boolean result = dao.update(usuario);
        assertTrue(result);
        System.out.println("Usuario actualizado: " + usuario);
    }

    @Test
    void delete() {
        UsuarioJdbc dao = UsuarioJdbcImpl.getInstance();
        Usuario usuario = new Usuario();
        usuario.setId(100);

        boolean result = dao.delete(usuario);
        assertTrue(result);
        System.out.println("Usuario eliminado con ID: " + usuario.getId());
    }

    @Test
    void findById() {
        UsuarioJdbc dao = UsuarioJdbcImpl.getInstance();


        Usuario usuario = dao.findById(100);

        assertNotNull(usuario);
        assertEquals(100, usuario.getId());
        assertNotNull(usuario.getNombre());
        assertNotNull(usuario.getEmail());

        System.out.println("Usuario encontrado: " + usuario);
    }
}
