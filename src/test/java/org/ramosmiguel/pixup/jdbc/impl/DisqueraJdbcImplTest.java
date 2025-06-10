package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.DisqueraJdbc;
import org.ramosmiguel.pixup.model.Disquera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisqueraJdbcImplTest {

    @Test
    void getInstance() {
        DisqueraJdbc dao = DisqueraJdbcImpl.getInstance();
        assertNotNull(dao);
    }

    @Test
    void findAll() {
        DisqueraJdbc dao = DisqueraJdbcImpl.getInstance();
        List<Disquera> disqueras = dao.findAll();
        assertNotNull(disqueras);
        assertTrue(disqueras.size() >= 0);
        disqueras.forEach(System.out::println);
    }

    @Test
    void save() {
        DisqueraJdbc dao = DisqueraJdbcImpl.getInstance();
        Disquera disquera = new Disquera();

        disquera.setId(1); // ID fijo manual
        disquera.setNombre("Disquera Manual");

        boolean result = dao.save(disquera);
        assertTrue(result);
        System.out.println("Disquera guardada: " + disquera);
    }

    @Test
    void update() {
        DisqueraJdbc dao = DisqueraJdbcImpl.getInstance();
        Disquera disquera = new Disquera();

        disquera.setId(1); // Mismo ID usado en `save`
        disquera.setNombre("Disquera Actualizada");

        boolean result = dao.update(disquera);
        assertTrue(result);
        System.out.println("Disquera actualizada: " + disquera);
    }

    @Test
    void delete() {
        DisqueraJdbc dao = DisqueraJdbcImpl.getInstance();
        Disquera disquera = new Disquera();

        disquera.setId(1); // Mismo ID usado en los tests anteriores

        boolean result = dao.delete(disquera);
        assertTrue(result);
        System.out.println("Disquera eliminada con ID: " + disquera.getId());
    }

    @Test
    void findById() {
        DisqueraJdbc dao = DisqueraJdbcImpl.getInstance();
        Disquera disquera = dao.findById(1); // Debe existir si `save()` ya corrió

        assertNotNull(disquera, "Disquera no encontrada");
        assertEquals(1, disquera.getId());
        assertNotNull(disquera.getNombre());

        System.out.println("Disquera encontrada: " + disquera);
    }
}
