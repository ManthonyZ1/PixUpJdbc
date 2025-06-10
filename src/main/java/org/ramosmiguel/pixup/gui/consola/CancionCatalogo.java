package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.CancionJdbc;
import org.ramosmiguel.pixup.jdbc.impl.CancionJdbcImpl;
import org.ramosmiguel.pixup.model.Cancion;
import org.ramosmiguel.pixup.util.ReadUtil;

public class CancionCatalogo extends Catalogos<Cancion>
{
    private final CancionJdbc dao = CancionJdbcImpl.getInstance();
    public static CancionCatalogo cancionCatalogo;

    private CancionCatalogo()
    {
        super();
        this.list = dao.findAll();
    }

    public static CancionCatalogo getInstance()
    {
        if(cancionCatalogo==null)
        {
            cancionCatalogo = new CancionCatalogo();
        }
        return cancionCatalogo;
    }


    @Override
    public Cancion newT() {
        return new Cancion();
    }

    @Override
    public boolean processNewT(Cancion cancion) {
        System.out.println("Titulo: ");
        cancion.setTitulo(ReadUtil.read());
        System.out.println("Duracion: ");
        cancion.setDuracion(ReadUtil.read());
        System.out.println("ID Disco: ");
        cancion.setDisco_id(ReadUtil.readInt());
        return true;
    }

    @Override
    public void processEditT(Cancion cancion) {
        System.out.println("ID: " + cancion.getId());
        System.out.println("Título actual: " + cancion.getTitulo());
        System.out.println("Duración actual: " + cancion.getDuracion());

        boolean editing = true;
        while (editing) {
            System.out.println("\n¿Qué campo desea editar?");
            System.out.println("1.- Título");
            System.out.println("2.- Duración");
            System.out.println("3.- Salir sin cambios");
            System.out.print("Opción: ");

            int opcion = ReadUtil.readInt();
            switch (opcion) {
                case 1:
                    System.out.print("Nuevo título: ");
                    cancion.setTitulo(ReadUtil.read());
                    System.out.println("→ Título actualizado a \"" + cancion.getTitulo() + "\"");
                    editing = false;
                    break;
                case 2:
                    System.out.print("Nueva duración: ");
                    cancion.setDuracion(ReadUtil.read());
                    System.out.println("→ Duración actualizada a \"" + cancion.getDuracion() + "\"");
                    editing = false;
                    break;
                case 3:
                    System.out.println("No se realizaron cambios.");
                    editing = false;
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
