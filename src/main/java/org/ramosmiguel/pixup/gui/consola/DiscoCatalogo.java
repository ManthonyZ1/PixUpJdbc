package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.DiscoJdbc;
import org.ramosmiguel.pixup.jdbc.impl.DiscoJdbcImpl;
import org.ramosmiguel.pixup.model.Disco;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.List;
import java.util.ArrayList;

public class DiscoCatalogo extends Catalogos<Disco> {
    private final DiscoJdbc dao = DiscoJdbcImpl.getInstance();
    private static DiscoCatalogo instancia;

    private DiscoCatalogo() {
        super();
        List<Disco> inicial = dao.findAll();
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    public static DiscoCatalogo getInstance() {
        if (instancia == null) {
            instancia = new DiscoCatalogo();
        }
        return instancia;
    }

    @Override
    public Disco newT() {
        return new Disco();
    }

    @Override
    public boolean processNewT(Disco disco) {
        System.out.print("Título: ");
        disco.setTitulo(ReadUtil.read());

        System.out.print("Precio: ");
        disco.setPrecio(ReadUtil.readInt());

        System.out.print("Existencias: ");
        disco.setExistencia(ReadUtil.readInt());

        System.out.print("Descuento: ");
        disco.setDescuento(ReadUtil.readInt());

        System.out.print("Fecha de lanzamiento (YYYY-MM-DD): ");
        disco.setFechaLanzamiento(ReadUtil.read());

        // Artista
        System.out.println("Seleccione un artista:");
        ArtistaCatalogo artistaCatalogo = ArtistaCatalogo.getInstance();
        artistaCatalogo.getList().forEach(a ->
                System.out.println(a.getId() + ") " + a.getNombre())
        );
        System.out.print("ID del artista: ");
        disco.setArtista_id(ReadUtil.readInt());

        // Disquera
        System.out.println("Seleccione una disquera:");
        DisqueraCatalogo disqueraCatalogo = DisqueraCatalogo.getInstance();
        disqueraCatalogo.getList().forEach(d ->
                System.out.println(d.getId() + ") " + d.getNombre())
        );
        System.out.print("ID de la disquera: ");
        disco.setDisquera_id(ReadUtil.readInt());

        // Género musical
        System.out.println("Seleccione un género musical:");
        GeneroMusicalCatalogo generoCatalogo = GeneroMusicalCatalogo.getInstance();
        generoCatalogo.getList().forEach(g ->
                System.out.println(g.getId() + ") " + g.getDescripcion())
        );
        System.out.print("ID del género musical: ");
        disco.setGeneroMusical_id(ReadUtil.readInt());

        return true;
    }


    @Override
    public void processEditT(Disco disco) {
        System.out.println("ID: " + disco.getId());
        System.out.println("1) Título:               " + disco.getTitulo());
        System.out.println("2) Precio:               " + disco.getPrecio());
        System.out.println("3) Existencias:          " + disco.getExistencia());
        System.out.println("4) Descuento:            " + disco.getDescuento());
        System.out.println("5) Fecha de lanzamiento: " + disco.getFechaLanzamiento());
        System.out.println("6) Editar todos los campos");
        System.out.println("7) Salir sin cambios");

        boolean editing = true;
        while (editing) {
            System.out.print("Seleccione campo a editar: ");
            switch (ReadUtil.readInt()) {
                case 1:
                    System.out.print("Nuevo título: ");
                    disco.setTitulo(ReadUtil.read());
                    System.out.println("→ Título actualizado");
                    editing = false;
                    break;
                case 2:
                    System.out.print("Nuevo precio: ");
                    disco.setPrecio(ReadUtil.readInt());
                    System.out.println("→ Precio actualizado");
                    editing = false;
                    break;
                case 3:
                    System.out.print("Nuevas existencias: ");
                    disco.setExistencia(ReadUtil.readInt());
                    System.out.println("→ Existencias actualizadas");
                    editing = false;
                    break;
                case 4:
                    System.out.print("Nuevo descuento: ");
                    disco.setDescuento(ReadUtil.readInt());
                    System.out.println("→ Descuento actualizado");
                    editing = false;
                    break;
                case 5:
                    System.out.print("Nueva fecha de lanzamiento (YYYY-MM-DD): ");
                    disco.setFechaLanzamiento(ReadUtil.read());
                    System.out.println("→ Fecha de lanzamiento actualizada");
                    editing = false;
                    break;
                case 6:
                    System.out.print("Nuevo título: ");
                    disco.setTitulo(ReadUtil.read());
                    System.out.print("Nuevo precio: ");
                    disco.setPrecio(ReadUtil.readInt());
                    System.out.print("Nuevas existencias: ");
                    disco.setExistencia(ReadUtil.readInt());
                    System.out.print("Nuevo descuento: ");
                    disco.setDescuento(ReadUtil.readInt());
                    System.out.print("Nueva fecha de lanzamiento (YYYY-MM-DD): ");
                    disco.setFechaLanzamiento(ReadUtil.read());
                    System.out.println("→ Todos los campos actualizados");
                    editing = false;
                    break;
                case 7:
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
