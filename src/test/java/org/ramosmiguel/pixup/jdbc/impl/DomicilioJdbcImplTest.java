package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.DomicilioJdbc;
import org.ramosmiguel.pixup.model.Domicilio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DomicilioJdbcImplTest {

    @Test
    void getInstance() {
        assertNotNull(DomicilioJdbcImpl.getInstance());
    }

    @Test
    void findAll() {
        DomicilioJdbc domicilioJdbc = DomicilioJdbcImpl.getInstance();
        List<Domicilio> list = domicilioJdbc.findAll();
        assertNotNull(list);
        assertTrue(list.size() >= 0);
        list.forEach(System.out::println);
    }

    @Test
    void save() {
        DomicilioJdbc domicilioJdbc = DomicilioJdbcImpl.getInstance();
        Domicilio domicilio = new Domicilio();

        domicilio.setCalle("Av. Reforma");
        domicilio.setNumExterior("123");
        domicilio.setNumInterior("4B");
        domicilio.setColonia_id(4);
        domicilio.setUsuario_id(100);


        boolean res = domicilioJdbc.save(domicilio);
        assertTrue(res);

        System.out.println(domicilio);
    }

    @Test
    void update() {
        DomicilioJdbc domicilioJdbc = DomicilioJdbcImpl.getInstance();
        Domicilio domicilio = new Domicilio();

        domicilio.setId(1);
        domicilio.setCalle("Calle Modificada");
        domicilio.setNumExterior("456");
        domicilio.setNumInterior("7C");
        domicilio.setColonia_id(1);
        domicilio.setUsuario_id(1);

        boolean res = domicilioJdbc.update(domicilio);
        assertTrue(res);

        System.out.println(domicilio);
    }

    @Test
    void delete() {
        DomicilioJdbc domicilioJdbc = DomicilioJdbcImpl.getInstance();
        Domicilio domicilio = new Domicilio();

        domicilio.setId(3);
        boolean res = domicilioJdbc.delete(domicilio);
        assertTrue(res);
    }

    @Test
    void findById() {
        DomicilioJdbc domicilioJdbc = DomicilioJdbcImpl.getInstance();
        Domicilio domicilio = domicilioJdbc.findById(1);

        assertNotNull(domicilio);
        assertEquals(1, domicilio.getId());
        System.out.println(domicilio);
    }
}
