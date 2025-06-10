package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.gui.LecturaAccion;
import org.ramosmiguel.pixup.negocio.Ejecutable;

public class MenuAgregarDisco extends LecturaAccion {
    private static MenuAgregarDisco instancia;

    private MenuAgregarDisco() { }

    public static MenuAgregarDisco getInstance() {
        if (instancia == null) {
            instancia = new MenuAgregarDisco();
        }
        return instancia;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("=== Agregar Disco ===");
        System.out.println("1.- Artista");
        System.out.println("2.- Disquera");
        System.out.println("3.- Género Musical");
        System.out.println("4.- Disco");
        System.out.println("5.- Canción");
        System.out.println("6.- Regresar");
    }

    @Override
    public int valorMinMenu() { return 1; }
    @Override
    public int valorMaxMenu() { return 6; }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;
        switch (opcion) {
            case 1:
                ejecutable = ArtistaCatalogo.getInstance();
                break;
            case 2:
                ejecutable = DisqueraCatalogo.getInstance();
                break;
            case 3:
                ejecutable = GeneroMusicalCatalogo.getInstance();
                break;
            case 4:
                ejecutable = DiscoCatalogo.getInstance();
                break;
            case 5:
                ejecutable = CancionCatalogo.getInstance();
                break;
            case 6:
                return;  // regresa al menú anterior
            default:
                System.out.println("Opción inválida");
                return;
        }
        ejecutable.setFlag(true);
        ejecutable.run();
    }
}
