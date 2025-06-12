package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.*;
import org.ramosmiguel.pixup.jdbc.GeneroMusicalJdbc;
import org.ramosmiguel.pixup.model.GeneroMusical;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GeneroMusicalJdbcImplTest {

    private GeneroMusicalJdbc dao;
    private GeneroMusical genero;

    @BeforeEach
    void setUp() {
        dao = GeneroMusicalJdbcImpl.getInstance();
        genero = new GeneroMusical();

        genero.setId((int) (System.currentTimeMillis() % 100000)); // ID único temporal
        genero.setDescripcion("Género de prueba");

        dao.save(genero);
    }

    @AfterEach
    void tearDown() {
        dao.delete(genero);
    }

    @Test
    void getInstance() {
        assertNotNull(GeneroMusicalJdbcImpl.getInstance());
    }

    @Test
    void findAll() {
        List<GeneroMusical> generos = dao.findAll();
        assertNotNull(generos);
        assertFalse(generos.isEmpty());
    }

    @Test
    void save() {
        GeneroMusical nuevo = new GeneroMusical();
        nuevo.setId((int) (System.nanoTime() % 100000));
        nuevo.setDescripcion("Nuevo género");

        boolean result = dao.save(nuevo);
        assertTrue(result);
        assertNotNull(nuevo.getId());

        dao.delete(nuevo);
    }

    @Test
    void update() {
        genero.setDescripcion("Género modificado");
        boolean result = dao.update(genero);
        assertTrue(result);

        GeneroMusical modificado = dao.findById(genero.getId());
        assertEquals("Género modificado", modificado.getDescripcion());
    }

    @Test
    void delete() {
        GeneroMusical temporal = new GeneroMusical();
        temporal.setId((int) (System.nanoTime() % 100000));
        temporal.setDescripcion("Eliminar género");

        dao.save(temporal);
        boolean result = dao.delete(temporal);
        assertTrue(result);

        GeneroMusical eliminado = dao.findById(temporal.getId());
        assertNull(eliminado);
    }

    @Test
    void findById() {
        GeneroMusical encontrado = dao.findById(genero.getId());
        assertNotNull(encontrado);
        assertEquals(genero.getId(), encontrado.getId());
    }
}
