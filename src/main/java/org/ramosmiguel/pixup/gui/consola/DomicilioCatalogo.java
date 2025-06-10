package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.DomicilioJdbc;
import org.ramosmiguel.pixup.jdbc.impl.DomicilioJdbcImpl;
import org.ramosmiguel.pixup.model.Domicilio;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.List;
import java.util.ArrayList;

public class DomicilioCatalogo extends Catalogos<Domicilio> {
    private final DomicilioJdbc dao = DomicilioJdbcImpl.getInstance();
    private static DomicilioCatalogo instancia;

    private DomicilioCatalogo() {
        super();
        List<Domicilio> inicial = dao.findAll();
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    public static DomicilioCatalogo getInstance() {
        if (instancia == null) {
            instancia = new DomicilioCatalogo();
        }
        return instancia;
    }

    @Override
    public Domicilio newT() {
        return new Domicilio();
    }

    @Override
    public boolean processNewT(Domicilio d) {
        System.out.print("Calle: ");
        d.setCalle(ReadUtil.read());
        System.out.print("Número Exterior: ");
        d.setNumExterior(ReadUtil.read());
        System.out.print("Número Interior: ");
        d.setNumInterior(ReadUtil.read());
        System.out.print("ID Colonia: ");
        d.setColonia_id(ReadUtil.readInt());
        System.out.print("ID Usuario: ");
        d.setUsuario_id(ReadUtil.readInt());
        return true;
    }

    @Override
    public void processEditT(Domicilio d) {
        System.out.println("\nEditando domicilio ID: " + d.getId());
        System.out.println("1) Calle:             " + d.getCalle());
        System.out.println("2) Número Exterior:   " + d.getNumExterior());
        System.out.println("3) Número Interior:   " + d.getNumInterior());
        System.out.println("4) ID Colonia:        " + d.getColonia_id());
        System.out.println("5) ID Usuario:        " + d.getUsuario_id());
        System.out.println("6) Editar todos los campos");
        System.out.println("7) Salir sin cambios");

        boolean editando = true;
        while (editando) {
            System.out.print("Seleccione campo a editar: ");
            switch (ReadUtil.readInt()) {
                case 1:
                    System.out.print("Nueva calle: ");
                    d.setCalle(ReadUtil.read());
                    editando = false;
                    break;
                case 2:
                    System.out.print("Nuevo número exterior: ");
                    d.setNumExterior(ReadUtil.read());
                    editando = false;
                    break;
                case 3:
                    System.out.print("Nuevo número interior: ");
                    d.setNumInterior(ReadUtil.read());
                    editando = false;
                    break;
                case 4:
                    System.out.print("Nuevo ID Colonia: ");
                    d.setColonia_id(ReadUtil.readInt());
                    editando = false;
                    break;
                case 5:
                    System.out.print("Nuevo ID Usuario: ");
                    d.setUsuario_id(ReadUtil.readInt());
                    editando = false;
                    break;
                case 6:
                    System.out.print("Nueva calle: ");
                    d.setCalle(ReadUtil.read());
                    System.out.print("Nuevo número exterior: ");
                    d.setNumExterior(ReadUtil.read());
                    System.out.print("Nuevo número interior: ");
                    d.setNumInterior(ReadUtil.read());
                    System.out.print("Nuevo ID Colonia: ");
                    d.setColonia_id(ReadUtil.readInt());
                    System.out.print("Nuevo ID Usuario: ");
                    d.setUsuario_id(ReadUtil.readInt());
                    editando = false;
                    break;
                case 7:
                    System.out.println("No se realizaron cambios.");
                    editando = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    @Override
    public void print() {
        this.list = dao.findAll();
        super.print();
    }

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

    @Override
    public void remove() {
        super.remove();
        dao.delete(t);
    }
}
