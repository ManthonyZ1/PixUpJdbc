package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.MunicipioJdbc;
import org.ramosmiguel.pixup.model.Municipio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioJdbcImplTest {

    @Test
    void getInstance() {
        MunicipioJdbc instancia = MunicipioJdbcImpl.getInstance();
        assertNotNull(instancia);
    }

    @Test
    void findAll() {
        MunicipioJdbc dao = MunicipioJdbcImpl.getInstance();
        List<Municipio> lista = dao.findAll();

        assertNotNull(lista);
        assertTrue(lista.size() >= 0);
        lista.forEach(System.out::println);
    }

    @Test
    void save() {
        MunicipioJdbc dao = MunicipioJdbcImpl.getInstance();
        Municipio municipio = new Municipio();


        municipio.setMunicipio("XOCHIMILCO");
        municipio.setId_estado(1);

        boolean resultado = dao.save(municipio);
        assertTrue(resultado);

        System.out.println("Municipio guardado exitosamente.");
    }

    @Test
    void update() {
        MunicipioJdbc dao = MunicipioJdbcImpl.getInstance();
        Municipio municipio = new Municipio();

        municipio.setId(2);
        municipio.setMunicipio("IZTAPALAPA");
        municipio.setId_estado(1);

        boolean resultado = dao.update(municipio);
        assertTrue(resultado);

        System.out.println("Municipio actualizado correctamente.");
    }

    @Test
    void delete() {
        MunicipioJdbc dao = MunicipioJdbcImpl.getInstance();
        List<Municipio> municipios = dao.findAll();
        Municipio ultimo = municipios.get(municipios.size() - 1);

        boolean result = dao.delete(ultimo);
        assertTrue(result);

        System.out.println("Disco eliminado con ID: " + ultimo.getId());
    }


    @Test
    void findById() {
        MunicipioJdbc dao = MunicipioJdbcImpl.getInstance();
        Municipio municipio = dao.findById(4);

        assertNotNull(municipio);
        assertEquals(4, municipio.getId());
        assertNotNull(municipio.getMunicipio());
        assertTrue(municipio.getId_estado() > 0);

        System.out.println("Municipio encontrado: " + municipio);
    }
}
