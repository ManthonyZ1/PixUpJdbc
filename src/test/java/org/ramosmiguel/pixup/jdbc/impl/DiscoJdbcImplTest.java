package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.DiscoJdbc;
import org.ramosmiguel.pixup.jdbc.impl.DiscoJdbcImpl;
import org.ramosmiguel.pixup.model.Disco;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscoJdbcImplTest {

    @Test
    void getInstance() {
        DiscoJdbc dao = DiscoJdbcImpl.getInstance();
        assertNotNull(dao);
    }

    @Test
    void findAll() {
        DiscoJdbc dao = DiscoJdbcImpl.getInstance();
        List<Disco> discos = dao.findAll();
        assertNotNull(discos);
        assertTrue(discos.size() >= 0);
        discos.forEach(System.out::println);
    }

    @Test
    void save() {
        DiscoJdbc dao = DiscoJdbcImpl.getInstance();
        Disco disco = new Disco();

        disco.setTitulo("Mr. Morale");
        disco.setPrecio(199);
        disco.setExistencia(30);
        disco.setDescuento(15);
        disco.setFechaLanzamiento("2025-01-15");
        disco.setImagen("test.jpg");
        disco.setArtista_id(2);        // Asegúrate que exista
        disco.setDisquera_id(1);       // Asegúrate que exista
        disco.setGeneroMusical_id(3000);  // Asegúrate que exista

        boolean result = dao.save(disco);
        assertTrue(result);

        System.out.println("Disco guardado: " + disco);
    }

    @Test
    void update() {
        DiscoJdbc dao = DiscoJdbcImpl.getInstance();
        Disco disco = dao.findById(1); // Usa un ID real que exista

        assertNotNull(disco);
        disco.setTitulo("Disco Modificado");
        disco.setPrecio(129);
        disco.setDescuento(20);

        boolean result = dao.update(disco);
        assertTrue(result);

        System.out.println("Disco actualizado: " + disco);
    }

    @Test
    void delete() {
        DiscoJdbc dao = DiscoJdbcImpl.getInstance();
        List<Disco> discos = dao.findAll();
        Disco ultimo = discos.get(discos.size() - 1); // Asume que hay al menos uno

        boolean result = dao.delete(ultimo);
        assertTrue(result);

        System.out.println("Disco eliminado con ID: " + ultimo.getId());
    }

    @Test
    void findById() {
        DiscoJdbc dao = DiscoJdbcImpl.getInstance();
        Disco disco = dao.findById(2); // Usa un ID que exista

        assertNotNull(disco);
        assertEquals(2, disco.getId());
        assertNotNull(disco.getTitulo());

        System.out.println("Disco encontrado: " + disco);
    }
}
