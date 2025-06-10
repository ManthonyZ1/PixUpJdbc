package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.ArtistaJdbc;
import org.ramosmiguel.pixup.jdbc.impl.ArtistaJdbcImpl;
import org.ramosmiguel.pixup.model.Artista;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class ArtistaCatalogo extends Catalogos<Artista> {
    private static ArtistaCatalogo instancia;
    private final ArtistaJdbc dao = ArtistaJdbcImpl.getInstance();

    private ArtistaCatalogo() {
        super();
        List<Artista> inicial = dao.findAll();
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    public static ArtistaCatalogo getInstance() {
        if (instancia == null) {
            instancia = new ArtistaCatalogo();
        }
        return instancia;
    }

    @Override
    public Artista newT() {
        return new Artista();
    }

    @Override
    public boolean processNewT(Artista artista) {
        System.out.println("Teclee el nombre del artista:");
        artista.setNombre(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(Artista artista) {
        System.out.println("ID del Artista: " + artista.getId());
        System.out.println("Artista a editar: " + artista.getNombre());
        System.out.println("Teclee el nuevo nombre del artista:");
        artista.setNombre(ReadUtil.read());
    }

    @Override
    public void print() {
        this.list = dao.findAll();
        if (isListEmpty()) {
            System.out.println("No hay artistas registrados.");
            return;
        }
        System.out.println("Ingrese el ID del artista a consultar:");
        Integer id = ReadUtil.readInt();
        Artista a = list.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (a == null) {
            System.out.println("ID inválido.");
        } else {
            System.out.println(a);
        }
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
