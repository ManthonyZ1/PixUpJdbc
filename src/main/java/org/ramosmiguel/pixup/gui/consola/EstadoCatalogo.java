package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.EstadoJdbc;
import org.ramosmiguel.pixup.jdbc.impl.EstadoJdbcImpl;
import org.ramosmiguel.pixup.model.Estado;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class EstadoCatalogo extends Catalogos<Estado> {

    // DAO para persistencia
    private final EstadoJdbc dao = EstadoJdbcImpl.getInstance();
    // instancia singleton
    private static EstadoCatalogo instancia;

    // constructor privado: carga la lista desde BD
    private EstadoCatalogo() {
        List<Estado> inicial = dao.findAll();
        // por seguridad, si la BD devolviera null
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    // getter de singleton
    public static EstadoCatalogo getInstance() {
        if (instancia == null) {
            instancia = new EstadoCatalogo();
        }
        return instancia;
    }

    // fábrica de objetos nuevos
    @Override
    public Estado newT() {
        return new Estado();
    }

    // lectura de datos para alta
    @Override
    public boolean processNewT(Estado estado) {
        System.out.println("Teclee un estado:");
        estado.setNombre(ReadUtil.read());
        return true;
    }

    // lectura de datos para edición
    @Override
    public void processEditT(Estado estado) {
        System.out.println("Id del Estado: " + estado.getId());
        System.out.println("Estado a editar: " + estado.getNombre());
        System.out.println("Teclee el nuevo valor del estado:");
        estado.setNombre(ReadUtil.read());
    }

    // override de add para persistir en BD
    @Override
    public void add() {
        super.add();
        dao.save(t);
    }


    @Override
    public void edit() {
        super.edit();
        dao.update(t);
    }

    // override de remove para borrar en BD
    @Override
    public void remove() {
        super.remove();
        dao.delete(t);
    }


    @Override
    public void print() {
        List<Estado> actual = dao.findAll();
        this.list = (actual != null) ? actual : new ArrayList<>();
        super.print();
    }
}
