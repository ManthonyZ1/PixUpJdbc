package org.ramosmiguel.pixup.jdbc.impl;

import org.junit.jupiter.api.Test;
import org.ramosmiguel.pixup.jdbc.EstadoJdbc;
import org.ramosmiguel.pixup.model.Estado;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoJdbcImplTest
{
    EstadoJdbc estadoJdbc;

    @Test
    void getInstance() {

        assertNotNull( EstadoJdbcImpl.getInstance( ) );
        EstadoJdbcImpl.getInstance();

    }

    @Test
    void findAll() {

        EstadoJdbc estadoJdbc = EstadoJdbcImpl.getInstance();
        List<Estado> list = estadoJdbc.findAll();
        assertNotNull(list);
        assertTrue(list.size() >= 0);
        assertEquals(0,list.size());
        list.stream().forEach(System.out::println);

    }

    @Test
    void save()
    {
        Estado estado = new Estado();
        boolean res = false;
        EstadoJdbc estadoJdbc = EstadoJdbcImpl.getInstance();
        estado.setNombre ("aguascalientes");
        res = estadoJdbc.save( estado );
        assertEquals( true, res);

        System.out.println(estado);

    }

    @Test
    void update()
    {

        Estado estado = new Estado();
        boolean res = false;
        estado.setNombre("EDOMEX");
        estado.setId(1);
        EstadoJdbc estadoJdbc = EstadoJdbcImpl.getInstance();
        res = estadoJdbc.update( estado );
        assertEquals( true, res);

        System.out.println( estado );
    }

    @Test
    void delete() {
        EstadoJdbc dao = EstadoJdbcImpl.getInstance();
        List<Estado> estados = dao.findAll();
        Estado ultimo = estados.get(estados.size() - 1);

        boolean result = dao.delete(ultimo);
        assertTrue(result);

        System.out.println("Disco eliminado con ID: " + ultimo.getId());
    }

    @Test
    void findById()
    {

        EstadoJdbc estadoJdbc = EstadoJdbcImpl.getInstance();
        Estado estado = estadoJdbc.findById(1);
        assertNotNull( estado );
        assertTrue("CDMX".equals(estado.getNombre()));
        assertEquals(1, estado.getId());
        System.out.println( estado );


    }


}