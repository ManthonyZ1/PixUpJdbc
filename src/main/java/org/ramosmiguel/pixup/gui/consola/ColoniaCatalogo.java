package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.ColoniaJdbc;
import org.ramosmiguel.pixup.jdbc.impl.ColoniaJdbcImpl;
import org.ramosmiguel.pixup.model.Colonia;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.List;
import java.util.ArrayList;

public class ColoniaCatalogo extends Catalogos<Colonia> {
    private final ColoniaJdbc dao = ColoniaJdbcImpl.getInstance();
    private static ColoniaCatalogo instancia;

    private ColoniaCatalogo() {
        super();
        List<Colonia> inicial = dao.findAll();
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    public static ColoniaCatalogo getInstance() {
        if (instancia == null) {
            instancia = new ColoniaCatalogo();
        }
        return instancia;
    }

    @Override
    public Colonia newT() {
        return new Colonia();
    }

    @Override
    public boolean processNewT(Colonia colonia) {
        System.out.print("Nombre: ");
        colonia.setNombre(ReadUtil.read());

        System.out.print("CP: ");
        colonia.setCp(ReadUtil.readInt());

        // Mostrar lista de municipios
        System.out.println("Seleccione un municipio:");
        MunicipioCatalogo municipioCatalogo = MunicipioCatalogo.getInstance();
        municipioCatalogo.getList().forEach(m ->
                System.out.println(m.getId() + ") " + m.getMunicipio())
        );

        System.out.print("Ingrese el ID del municipio: ");
        colonia.setMunicipio_id(ReadUtil.readInt());

        return true;
    }

    /**
     * Permite elegir qué campo editar: nombre, CP o ambos
     */
    @Override
    public void processEditT(Colonia colonia) {
        System.out.println("ID: " + colonia.getId());
        System.out.println("1) Nombre: " + colonia.getNombre());
        System.out.println("2) CP:     " + colonia.getCp());
        System.out.println("3) Editar ambos");
        System.out.println("4) Salir sin cambios");

        boolean ejecutar = true;
        while (ejecutar) {
            System.out.print("Opción a editar: ");
            switch (ReadUtil.readInt()) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    colonia.setNombre(ReadUtil.read());
                    System.out.println("→ Nombre actualizado");
                    ejecutar = false;
                    break;
                case 2:
                    System.out.print("Nuevo CP: ");
                    colonia.setCp(ReadUtil.readInt());
                    System.out.println("→ CP actualizado");
                    ejecutar = false;
                    break;
                case 3:
                    System.out.print("Nuevo nombre: ");
                    colonia.setNombre(ReadUtil.read());
                    System.out.print("Nuevo CP: ");
                    colonia.setCp(ReadUtil.readInt());
                    System.out.println("→ Nombre y CP actualizados");
                    ejecutar = false;
                    break;
                case 4:
                    System.out.println("No se realizaron cambios.");
                    ejecutar = false;
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
