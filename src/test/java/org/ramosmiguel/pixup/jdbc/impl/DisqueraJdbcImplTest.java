package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.*;
import org.ramosmiguel.pixup.jdbc.DisqueraJdbc;
import org.ramosmiguel.pixup.model.Disquera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class DisqueraJdbcImplTest {

    private DisqueraJdbc dao;
    private Disquera disquera;

    @BeforeEach
    void setUp() {
        dao = DisqueraJdbcImpl.getInstance();
        disquera = new Disquera();

        disquera.setId((int)(System.currentTimeMillis() % 100000));
        disquera.setNombre("Disquera de prueba");

        dao.save(disquera);
    }

    @AfterEach
    void tearDown() {
        dao.delete(disquera);
    }

    @Test
    void getInstance() {
        assertNotNull(DisqueraJdbcImpl.getInstance());
    }

    @Test
    void findAll() {
        List<Disquera> disqueras = dao.findAll();
        assertNotNull(disqueras);
        assertFalse(disqueras.isEmpty());
    }

    @Test
    void save() {
        Disquera nueva = new Disquera();
        nueva.setId((int)(System.nanoTime() % 100000));
        nueva.setNombre("Nueva Disquera");

        boolean result = dao.save(nueva);
        assertTrue(result);

        Disquera encontrada = dao.findById(nueva.getId());
        assertNotNull(encontrada);
        assertEquals("Nueva Disquera", encontrada.getNombre());

        dao.delete(nueva);
    }

    @Test
    void update() {
        disquera.setNombre("Disquera Actualizada");
        boolean result = dao.update(disquera);
        assertTrue(result);

        Disquera actualizada = dao.findById(disquera.getId());
        assertEquals("Disquera Actualizada", actualizada.getNombre());
    }

    @Test
    void delete() {
        Disquera temporal = new Disquera();
        temporal.setId((int)(System.nanoTime() % 100000));
        temporal.setNombre("Temporal");

        dao.save(temporal);
        boolean result = dao.delete(temporal);
        assertTrue(result);

        Disquera eliminada = dao.findById(temporal.getId());
        assertNull(eliminada);
    }

    @Test
    void findById() {
        Disquera encontrada = dao.findById(disquera.getId());
        assertNotNull(encontrada);
        assertEquals(disquera.getId(), encontrada.getId());
        assertEquals(disquera.getNombre(), encontrada.getNombre());
    }
}
