package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.ArtistaJdbc;
import org.ramosmiguel.pixup.model.Artista;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaJdbcImplTest {

    @Test
    void getInstance() {
        ArtistaJdbc dao = ArtistaJdbcImpl.getInstance();
        assertNotNull(dao);
    }

    @Test
    void findAll() {
        ArtistaJdbc dao = ArtistaJdbcImpl.getInstance();
        List<Artista> artistas = dao.findAll();
        assertNotNull(artistas);
        assertTrue(artistas.size() >= 0);
        artistas.forEach(System.out::println);
    }

    @Test
    void save() {
        ArtistaJdbc dao = ArtistaJdbcImpl.getInstance();
        Artista artista = new Artista();

        artista.setId(1); // ID fijo manual
        artista.setNombre("Artista Manual");

        boolean result = dao.save(artista);
        assertTrue(result);

        System.out.println("Artista guardado: " + artista);
    }

    @Test
    void update() {
        ArtistaJdbc dao = ArtistaJdbcImpl.getInstance();
        Artista artista = new Artista();

        artista.setId(1); // ID que se usó en el test `save`
        artista.setNombre("Artista Modificado");

        boolean result = dao.update(artista);
        assertTrue(result);

        System.out.println("Artista actualizado: " + artista);
    }

    @Test
    void delete() {
        ArtistaJdbc dao = ArtistaJdbcImpl.getInstance();
        Artista artista = new Artista();

        artista.setId(4); // ID que se usó en los tests anteriores

        boolean result = dao.delete(artista);
        assertTrue(result);

        System.out.println("Artista eliminado con ID: " + artista.getId());
    }

    @Test
    void findById() {
        ArtistaJdbc dao = ArtistaJdbcImpl.getInstance();

        Artista artista = dao.findById(2); // El ID debe existir si se ejecutó `save` previamente

        assertNotNull(artista);
        assertEquals(2, artista.getId());
        assertNotNull(artista.getNombre());

        System.out.println("Artista encontrado: " + artista);
    }
}
