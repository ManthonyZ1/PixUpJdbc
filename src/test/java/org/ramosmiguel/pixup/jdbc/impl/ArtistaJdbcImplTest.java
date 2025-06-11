package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.*;
import org.ramosmiguel.pixup.jdbc.ArtistaJdbc;
import org.ramosmiguel.pixup.model.Artista;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ArtistaJdbcImplTest {

    private ArtistaJdbc dao;
    private Artista artista;

    @BeforeEach
    void setUp() {
        dao = ArtistaJdbcImpl.getInstance();
        artista = new Artista();
        artista.setNombre("Artista Temporal");
        dao.save(artista); // Guardamos antes de cada test
    }

    @AfterEach
    void tearDown() {
        dao.delete(artista); // Eliminamos después de cada test
    }

    @Test
    void getInstance() {
        ArtistaJdbc instancia = ArtistaJdbcImpl.getInstance();
        assertNotNull(instancia);
    }

    @Test
    void findAll() {
        List<Artista> artistas = dao.findAll();
        assertNotNull(artistas);
        assertFalse(artistas.isEmpty()); // Porque ya insertamos uno en setUp
    }

    @Test
    void save() {
        Artista nuevo = new Artista();
        nuevo.setNombre("Artista Save Test");
        boolean resultado = dao.save(nuevo);
        assertTrue(resultado);
        assertNotNull(nuevo.getId());
        dao.delete(nuevo); // Limpieza manual
    }

    @Test
    void update() {
        artista.setNombre("Artista Modificado");
        boolean result = dao.update(artista);
        assertTrue(result);

        Artista modificado = dao.findById(artista.getId());
        assertEquals("Artista Modificado", modificado.getNombre());
    }

    @Test
    void findById() {
        Artista encontrado = dao.findById(artista.getId());
        assertNotNull(encontrado);
        assertEquals(artista.getNombre(), encontrado.getNombre());
    }

    @Test
    void delete() {
        Artista aBorrar = new Artista();
        aBorrar.setNombre("Artista Borrar Test");
        dao.save(aBorrar);
        boolean eliminado = dao.delete(aBorrar);
        assertTrue(eliminado);

        Artista resultado = dao.findById(aBorrar.getId());
        assertNull(resultado);
    }
}
