package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.GeneroMusicalJdbc;
import org.ramosmiguel.pixup.model.GeneroMusical;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneroMusicalJdbcImplTest {

    @Test
    void getInstance() {
        GeneroMusicalJdbc dao = GeneroMusicalJdbcImpl.getInstance();
        assertNotNull(dao);
    }

    @Test
    void findAll() {
        GeneroMusicalJdbc dao = GeneroMusicalJdbcImpl.getInstance();
        List<GeneroMusical> generos = dao.findAll();
        assertNotNull(generos);
        assertTrue(generos.size() >= 0);
        generos.forEach(System.out::println);
    }

    @Test
    void save() {
        GeneroMusicalJdbc dao = GeneroMusicalJdbcImpl.getInstance();
        GeneroMusical genero = new GeneroMusical();

        genero.setId(3000); // ID manual
        genero.setDescripcion("Género de prueba");

        boolean result = dao.save(genero);
        assertTrue(result);

        System.out.println("Género guardado: " + genero);
    }

    @Test
    void update() {
        GeneroMusicalJdbc dao = GeneroMusicalJdbcImpl.getInstance();
        GeneroMusical genero = new GeneroMusical();

        genero.setId(3000); // Mismo ID que en `save`
        genero.setDescripcion("Género modificado");

        boolean result = dao.update(genero);
        assertTrue(result);

        System.out.println("Género actualizado: " + genero);
    }

    @Test
    void delete() {
        GeneroMusicalJdbc dao = GeneroMusicalJdbcImpl.getInstance();
        GeneroMusical genero = new GeneroMusical();

        genero.setId(1); // Mismo ID que en `save`
        boolean result = dao.delete(genero);
        assertTrue(result);

        System.out.println("Género eliminado con ID: " + genero.getId());
    }

    @Test
    void findById() {
        GeneroMusicalJdbc dao = GeneroMusicalJdbcImpl.getInstance();
        GeneroMusical genero = dao.findById(3000); // Mismo ID que en `save`

        assertNotNull(genero);
        assertEquals(3000, genero.getId());
        assertNotNull(genero.getDescripcion());

        System.out.println("Género encontrado: " + genero);
    }
}
