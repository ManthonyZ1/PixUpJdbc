package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.CancionJdbc;
import org.ramosmiguel.pixup.jdbc.DiscoJdbc;
import org.ramosmiguel.pixup.model.Cancion;
import org.ramosmiguel.pixup.model.Disco;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancionJdbcImplTest {

    private final CancionJdbc cancionDao = CancionJdbcImpl.getInstance();
    private final DiscoJdbc discoDao = DiscoJdbcImpl.getInstance();

    @Test
    void getInstance() {
        assertNotNull(cancionDao);
    }

    @Test
    void findAll() {

        Disco disco = new Disco();
        disco.setTitulo("Disco para findAll canción");
        disco.setPrecio(100);
        disco.setExistencia(5);
        disco.setDescuento(0);
        disco.setFechaLanzamiento("2025-06-12");
        disco.setImagen("disco_findall.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);
        discoDao.save(disco);


        Cancion cancion = new Cancion();
        cancion.setTitulo("Canción findAll");
        cancion.setDuracion("03:00");
        cancion.setDisco_id(disco.getId());
        cancionDao.save(cancion);


        List<Cancion> canciones = cancionDao.findAll();
        assertNotNull(canciones);
        assertFalse(canciones.isEmpty(), "La lista no debe estar vacía");


        cancionDao.delete(cancion);
        discoDao.delete(disco);
    }


    @Test
    void save() {

        Disco disco = new Disco();
        disco.setTitulo("Disco para canción");
        disco.setPrecio(100);
        disco.setExistencia(5);
        disco.setDescuento(0);
        disco.setFechaLanzamiento("2025-06-12");
        disco.setImagen("disco_cancion.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);
        discoDao.save(disco);


        Cancion cancion = new Cancion();
        cancion.setTitulo("Luna llena");
        cancion.setDuracion("03:45");
        cancion.setDisco_id(disco.getId());

        boolean result = cancionDao.save(cancion);
        assertTrue(result);
        assertNotNull(cancion.getId());
        System.out.println("Canción guardada: " + cancion);

        // Limpieza
        cancionDao.delete(cancion);
        discoDao.delete(disco);
    }

    @Test
    void update() {

        Disco disco = new Disco();
        disco.setTitulo("Disco Update");
        disco.setPrecio(120);
        disco.setExistencia(10);
        disco.setDescuento(2);
        disco.setFechaLanzamiento("2025-06-12");
        disco.setImagen("update.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);
        discoDao.save(disco);


        Cancion cancion = new Cancion();
        cancion.setTitulo("Estrella fugaz");
        cancion.setDuracion("03:20");
        cancion.setDisco_id(disco.getId());
        cancionDao.save(cancion);


        cancion.setTitulo("Estrella brillante");
        cancion.setDuracion("04:10");
        boolean result = cancionDao.update(cancion);
        assertTrue(result);

        Cancion actualizada = cancionDao.findById(cancion.getId());
        assertEquals("Estrella brillante", actualizada.getTitulo());


        cancionDao.delete(cancion);
        discoDao.delete(disco);
    }

    @Test
    void delete() {
        Disco disco = new Disco();
        disco.setTitulo("Disco para borrar");
        disco.setPrecio(110);
        disco.setExistencia(7);
        disco.setDescuento(1);
        disco.setFechaLanzamiento("2025-06-12");
        disco.setImagen("borrar.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);
        discoDao.save(disco);

        Cancion cancion = new Cancion();
        cancion.setTitulo("Temporal");
        cancion.setDuracion("02:40");
        cancion.setDisco_id(disco.getId());
        cancionDao.save(cancion);

        boolean eliminado = cancionDao.delete(cancion);
        assertTrue(eliminado);

        Cancion encontrada = cancionDao.findById(cancion.getId());
        assertNull(encontrada);

        discoDao.delete(disco);
    }

    @Test
    void findById() {
        Disco disco = new Disco();
        disco.setTitulo("Disco búsqueda");
        disco.setPrecio(100);
        disco.setExistencia(3);
        disco.setDescuento(0);
        disco.setFechaLanzamiento("2025-06-12");
        disco.setImagen("buscar.jpg");
        disco.setArtista_id(1);
        disco.setDisquera_id(89501);
        disco.setGeneroMusical_id(1);
        discoDao.save(disco);

        Cancion cancion = new Cancion();
        cancion.setTitulo("Buscar canción");
        cancion.setDuracion("03:00");
        cancion.setDisco_id(disco.getId());
        cancionDao.save(cancion);

        Cancion encontrada = cancionDao.findById(cancion.getId());
        assertNotNull(encontrada);
        assertEquals("Buscar canción", encontrada.getTitulo());

        // Limpieza
        cancionDao.delete(cancion);
        discoDao.delete(disco);
    }
}
