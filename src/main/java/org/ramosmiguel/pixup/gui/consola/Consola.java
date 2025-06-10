package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.gui.LecturaAccion;
import org.ramosmiguel.pixup.negocio.Ejecutable;

public class Consola extends LecturaAccion
{
    private static Consola consola;

    private Consola()
    {
    }

    public static Consola getInstance( )
    {
        if(consola==null)
        {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Registrar Usuario");
        System.out.println("2.- Agregar Disco");
        System.out.println("3.- Salir");
    }

    @Override
    public int valorMinMenu() { return 1; }
    @Override
    public int valorMaxMenu() { return 3; }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;
        switch (opcion) {
            case 1:
                ejecutable = MenuRegistrarUsuario.getInstance();
                break;
            case 2:
                ejecutable = MenuAgregarDisco.getInstance();
                break;
            case 3:
                return;  // sale de la consola
            default:
                System.out.println("Opción inválida");
                return;
        }
        ejecutable.setFlag(true);
        ejecutable.run();
    }
}
