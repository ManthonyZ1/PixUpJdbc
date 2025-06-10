package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.GeneroMusicalJdbc;
import org.ramosmiguel.pixup.jdbc.impl.GeneroMusicalJdbcImpl;
import org.ramosmiguel.pixup.model.GeneroMusical;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalCatalogo extends Catalogos<GeneroMusical> {
    private final GeneroMusicalJdbc dao = GeneroMusicalJdbcImpl.getInstance();
    private static GeneroMusicalCatalogo instancia;

    private GeneroMusicalCatalogo() {
        super();
        List<GeneroMusical> inicial = dao.findAll();
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    public static GeneroMusicalCatalogo getInstance() {
        if (instancia == null) {
            instancia = new GeneroMusicalCatalogo();
        }
        return instancia;
    }

    @Override
    public GeneroMusical newT() {
        return new GeneroMusical();
    }

    @Override
    public boolean processNewT(GeneroMusical g) {
        System.out.print("Descripción: ");
        g.setDescripcion(ReadUtil.read());
        return true;
    }

    /**
     * Permite elegir si editar la descripción o salir sin cambios.
     */
    @Override
    public void processEditT(GeneroMusical g) {
        System.out.println("ID: " + g.getId());
        System.out.println("Descripción actual: " + g.getDescripcion());
        System.out.println("1) Cambiar descripción");
        System.out.println("2) Salir sin cambios");

        boolean editando = true;
        while (editando) {
            System.out.print("Opción: ");
            switch (ReadUtil.readInt()) {
                case 1:
                    System.out.print("Nueva descripción: ");
                    g.setDescripcion(ReadUtil.read());
                    System.out.println("→ Descripción actualizada");
                    editando = false;
                    break;
                case 2:
                    System.out.println("No se realizaron cambios.");
                    editando = false;
                    break;
                default:
                    System.out.println("Opción inválida, inténtelo de nuevo.");
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
