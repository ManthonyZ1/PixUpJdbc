package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.ColoniaJdbc;
import org.ramosmiguel.pixup.jdbc.DiscoJdbc;
import org.ramosmiguel.pixup.model.Colonia;
import org.ramosmiguel.pixup.model.Disco;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColoniaJdbcImplTest {

    @Test
    void getInstance() {
        ColoniaJdbc instancia = ColoniaJdbcImpl.getInstance();
        assertNotNull(instancia);
    }

    @Test
    void findAll() {
        ColoniaJdbc coloniaJdbc = ColoniaJdbcImpl.getInstance();
        List<Colonia> colonias = coloniaJdbc.findAll();

        assertNotNull(colonias);
        assertTrue(colonias.size() >= 0);
        colonias.forEach(System.out::println);
    }

    @Test
    void save() {
        ColoniaJdbc coloniaJdbc = ColoniaJdbcImpl.getInstance();
        Colonia colonia = new Colonia();

        colonia.setNombre("COLONIA DEL VALLE");
        colonia.setCp(31000);               // Código postal
        colonia.setMunicipio_id(4);         // Asegúrate que este municipio exista

        boolean res = coloniaJdbc.save(colonia);
        assertTrue(res);

        System.out.println("Colonia guardada: " + colonia);
    }

    @Test
    void update() {
        ColoniaJdbc coloniaJdbc = ColoniaJdbcImpl.getInstance();
        Colonia colonia = new Colonia();

        colonia.setId(4);                   // Asegúrate que esta colonia exista
        colonia.setNombre("COLONIA ACTUALIZADA");
        colonia.setCp(32000);
        colonia.setMunicipio_id(4);         // El municipio también debe existir

        boolean res = coloniaJdbc.update(colonia);
        assertTrue(res);

        System.out.println("Colonia actualizada: " + colonia);
    }

    @Test
    void delete() {
        ColoniaJdbc dao = ColoniaJdbcImpl.getInstance();
        List<Colonia> colonias = dao.findAll();
        Colonia ultimo = colonias.get(colonias.size() - 1); // Asume que hay al menos uno

        boolean result = dao.delete(ultimo);
        assertTrue(result);

        System.out.println("Disco eliminado con ID: " + ultimo.getId());
    }

    @Test
    void findById() {
        ColoniaJdbc coloniaJdbc = ColoniaJdbcImpl.getInstance();
        Colonia colonia = coloniaJdbc.findById(4); // Este ID debe existir

        assertNotNull(colonia);
        assertEquals(4, colonia.getId());
        assertNotNull(colonia.getNombre());
        assertNotNull(colonia.getCp());
        assertNotNull(colonia.getMunicipio_id());

        System.out.println("Colonia encontrada: " + colonia);
    }
}
