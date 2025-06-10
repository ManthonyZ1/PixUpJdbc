package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.DisqueraJdbc;
import org.ramosmiguel.pixup.jdbc.impl.DisqueraJdbcImpl;
import org.ramosmiguel.pixup.model.Disquera;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class DisqueraCatalogo extends Catalogos<Disquera> {
    private final DisqueraJdbc dao = DisqueraJdbcImpl.getInstance();
    private static DisqueraCatalogo instancia;

    private DisqueraCatalogo() {
        super();
        List<Disquera> inicial = dao.findAll();
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    public static DisqueraCatalogo getInstance() {
        if (instancia == null) {
            instancia = new DisqueraCatalogo();
        }
        return instancia;
    }

    @Override
    public Disquera newT() {
        return new Disquera();
    }

    @Override
    public boolean processNewT(Disquera disquera) {
        System.out.print("Nombre de la disquera: ");
        disquera.setNombre(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Disquera disquera) {
        System.out.println("ID: " + disquera.getId());
        System.out.println("Nombre actual: " + disquera.getNombre());
        System.out.println("1) Cambiar nombre");
        System.out.println("2) Salir sin cambios");
        boolean editando = true;
        while (editando) {
            System.out.print("Opción: ");
            switch (ReadUtil.readInt()) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    disquera.setNombre(ReadUtil.read());
                    System.out.println("→ Nombre actualizado");
                    editando = false;
                    break;
                case 2:
                    System.out.println("No se realizaron cambios.");
                    editando = false;
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
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
