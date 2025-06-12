package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.DiscoJdbc;
import org.ramosmiguel.pixup.model.Disco;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscoJdbcImplTest {

    private final DiscoJdbc dao = DiscoJdbcImpl.getInstance();

    @Test
    void getInstance() {
        assertNotNull(dao);
    }

    @Test
    void findAll() {

        Disco disco = new Disco();
        disco.setTitulo("Disco para findAll");
        disco.setPrecio(100);
        disco.setExistencia(10);
        disco.setDescuento(0);
        disco.setFechaLanzamiento("2025-06-15");
        disco.setImagen("findall.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);

        boolean guardado = dao.save(disco);
        assertTrue(guardado);

        List<Disco> discos = dao.findAll();
        assertNotNull(discos);
        assertFalse(discos.isEmpty());

        dao.delete(disco); // Limpieza
    }

    @Test
    void save() {
        Disco nuevo = new Disco();
        nuevo.setTitulo("Nuevo Disco");
        nuevo.setPrecio(200);
        nuevo.setExistencia(20);
        nuevo.setDescuento(10);
        nuevo.setFechaLanzamiento("2025-06-15");
        nuevo.setImagen("nuevo.jpg");
        nuevo.setArtista_id(1);
        nuevo.setDisquera_id(89501);
        nuevo.setGeneroMusical_id(1);

        boolean resultado = dao.save(nuevo);
        assertTrue(resultado);
        assertNotNull(nuevo.getId());

        dao.delete(nuevo);
    }

    @Test
    void update() {
        Disco disco = new Disco();
        disco.setTitulo("Disco a actualizar");
        disco.setPrecio(150);
        disco.setExistencia(10);
        disco.setDescuento(5);
        disco.setFechaLanzamiento("2025-06-01");
        disco.setImagen("actualizar.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);

        dao.save(disco);

        // Actualizar valores
        disco.setTitulo("Disco Actualizado");
        disco.setPrecio(180);
        disco.setDescuento(8);

        boolean actualizado = dao.update(disco);
        assertTrue(actualizado);

        Disco verificado = dao.findById(disco.getId());
        assertEquals("Disco Actualizado", verificado.getTitulo());
        assertEquals(180, verificado.getPrecio());
        assertEquals(8, verificado.getDescuento());

        dao.delete(disco); // Limpieza
    }

    @Test
    void delete() {
        Disco extra = new Disco();
        extra.setTitulo("Disco Temporal");
        extra.setPrecio(120);
        extra.setExistencia(5);
        extra.setDescuento(0);
        extra.setFechaLanzamiento("2025-06-11");
        extra.setImagen("temp.jpg");
        extra.setArtista_id(1);
        extra.setDisquera_id(89501);
        extra.setGeneroMusical_id(1);

        dao.save(extra);
        boolean eliminado = dao.delete(extra);
        assertTrue(eliminado);

        Disco verificacion = dao.findById(extra.getId());
        assertNull(verificacion);
    }

    @Test
    void findById() {
        Disco disco = new Disco();
        disco.setTitulo("Buscar Disco");
        disco.setPrecio(130);
        disco.setExistencia(8);
        disco.setDescuento(5);
        disco.setFechaLanzamiento("2025-06-10");
        disco.setImagen("buscar.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);

        dao.save(disco);
        Disco encontrado = dao.findById(disco.getId());

        assertNotNull(encontrado);
        assertEquals("Buscar Disco", encontrado.getTitulo());

        dao.delete(disco);
    }
}
