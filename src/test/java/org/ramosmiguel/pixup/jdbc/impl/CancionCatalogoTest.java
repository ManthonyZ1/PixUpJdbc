package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.CancionJdbc;
import org.ramosmiguel.pixup.model.Cancion;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancionJdbcImplTest {

    @Test
    void getInstance() {
        CancionJdbc dao = CancionJdbcImpl.getInstance();
        assertNotNull(dao);
    }

    @Test
    void findAll() {
        CancionJdbc dao = CancionJdbcImpl.getInstance();
        List<Cancion> canciones = dao.findAll();
        assertNotNull(canciones);
        assertTrue(canciones.size() >= 0);
        canciones.forEach(System.out::println);
    }

    @Test
    void save() {
        CancionJdbc dao = CancionJdbcImpl.getInstance();
        Cancion cancion = new Cancion();

        cancion.setTitulo("Luna llena");
        cancion.setDuracion("03:45");
        cancion.setDisco_id(3); // Asegúrate que el disco con ID = 1 exista

        boolean result = dao.save(cancion);
        assertTrue(result);

        System.out.println("Canción guardada: " + cancion);
    }

    @Test
    void update() {
        CancionJdbc dao = CancionJdbcImpl.getInstance();
        Cancion cancion = new Cancion();

        cancion.setId(6); // Usa un ID real que ya exista
        cancion.setTitulo("Luna creciente");
        cancion.setDuracion("04:00");
        cancion.setDisco_id(3); // Disco válido

        boolean result = dao.update(cancion);
        assertTrue(result);

        System.out.println("Canción actualizada: " + cancion);
    }

    @Test
    void delete() {
        CancionJdbc dao = CancionJdbcImpl.getInstance();
        Cancion cancion = new Cancion();

        cancion.setId(7); // Asegúrate que este ID exista para eliminarlo
        boolean result = dao.delete(cancion);
        assertTrue(result);

        System.out.println("Canción eliminada con ID: " + cancion.getId());
    }

    @Test
    void findById() {
        CancionJdbc dao = CancionJdbcImpl.getInstance();
        Cancion cancion = dao.findById(6); // Usa un ID que exista

        assertNotNull(cancion);
        assertEquals(6, cancion.getId());
        assertNotNull(cancion.getTitulo());

        System.out.println("Canción encontrada: " + cancion);
    }
}
