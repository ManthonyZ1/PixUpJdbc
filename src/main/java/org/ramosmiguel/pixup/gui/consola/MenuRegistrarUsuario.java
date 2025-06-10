package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.gui.LecturaAccion;
import org.ramosmiguel.pixup.negocio.Ejecutable;

public class MenuRegistrarUsuario extends LecturaAccion {
    private static MenuRegistrarUsuario instancia;

    private MenuRegistrarUsuario() { }

    public static MenuRegistrarUsuario getInstance() {
        if (instancia == null) {
            instancia = new MenuRegistrarUsuario();
        }
        return instancia;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("=== Registrar Usuario ===");
        System.out.println("1.- Estado");
        System.out.println("2.- Municipio");
        System.out.println("3.- Colonia");
        System.out.println("4.- Domicilio");
        System.out.println("5.- Tipo Domicilio");
        System.out.println("6.- Usuario");
        System.out.println("7.- Regresar");
    }

    @Override
    public int valorMinMenu() { return 1; }
    @Override
    public int valorMaxMenu() { return 7; }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;
        switch (opcion) {
            case 1:
                ejecutable = EstadoCatalogo.getInstance();
                break;
            case 2:
                ejecutable = MunicipioCatalogo.getInstance();
                break;
            case 3:
                ejecutable = ColoniaCatalogo.getInstance();
                break;
            case 4:
                ejecutable = DomicilioCatalogo.getInstance();
                break;
            case 5:
                ejecutable = TipoDomicilioCatalogo.getInstance();
                break;
            case 6:
                ejecutable = UsuarioCatalogo.getInstance();
                break;
            case 7:
                return;  // regresa al menú anterior
            default:
                System.out.println("Opción inválida");
                return;
        }
        ejecutable.setFlag(true);
        ejecutable.run();
    }
}
