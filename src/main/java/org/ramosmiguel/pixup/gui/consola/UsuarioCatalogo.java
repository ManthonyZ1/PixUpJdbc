package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.UsuarioJdbc;
import org.ramosmiguel.pixup.jdbc.impl.UsuarioJdbcImpl;
import org.ramosmiguel.pixup.model.Usuario;
import org.ramosmiguel.pixup.util.ReadUtil;

import java.util.ArrayList;
import java.util.List;

public class UsuarioCatalogo extends Catalogos<Usuario> {
    private final UsuarioJdbc dao = UsuarioJdbcImpl.getInstance();
    private static UsuarioCatalogo instancia;

    private UsuarioCatalogo() {
        super();
        List<Usuario> inicial = dao.findAll();
        this.list = (inicial != null) ? inicial : new ArrayList<>();
    }

    public static UsuarioCatalogo getInstance() {
        if (instancia == null) {
            instancia = new UsuarioCatalogo();
        }
        return instancia;
    }

    @Override
    public Usuario newT() {
        return new Usuario();
    }

    @Override
    public boolean processNewT(Usuario u) {
        System.out.print("Nombre: ");
        u.setNombre(ReadUtil.read());
        System.out.print("Apellido Paterno: ");
        u.setPrimerApellido(ReadUtil.read());
        System.out.print("Apellido Materno: ");
        u.setSegundoApellido(ReadUtil.read());
        System.out.print("Contraseña: ");
        u.setPassword(ReadUtil.read());
        System.out.print("Email: ");
        u.setEmail(ReadUtil.read());
        return true;
    }

    /**
     * Permite editar un campo específico, todos, o salir sin cambios.
     */
    @Override
    public void processEditT(Usuario u) {
        System.out.println("\nEditando usuario ID: " + u.getId());
        System.out.println("1) Nombre:            " + u.getNombre());
        System.out.println("2) Apellido Paterno:  " + u.getPrimerApellido());
        System.out.println("3) Apellido Materno:  " + u.getSegundoApellido());
        System.out.println("4) Contraseña:        " + u.getPassword());
        System.out.println("5) Email:             " + u.getEmail());
        System.out.println("6) Editar todos");
        System.out.println("7) Salir sin cambios");

        boolean editando = true;
        while (editando) {
            System.out.print("Seleccione campo a editar: ");
            switch (ReadUtil.readInt()) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    u.setNombre(ReadUtil.read());
                    System.out.println("→ Nombre actualizado");
                    editando = false;
                    break;
                case 2:
                    System.out.print("Nuevo apellido paterno: ");
                    u.setPrimerApellido(ReadUtil.read());
                    System.out.println("→ Apellido paterno actualizado");
                    editando = false;
                    break;
                case 3:
                    System.out.print("Nuevo apellido materno: ");
                    u.setSegundoApellido(ReadUtil.read());
                    System.out.println("→ Apellido materno actualizado");
                    editando = false;
                    break;
                case 4:
                    System.out.print("Nueva contraseña: ");
                    u.setPassword(ReadUtil.read());
                    System.out.println("→ Contraseña actualizada");
                    editando = false;
                    break;
                case 5:
                    System.out.print("Nuevo email: ");
                    u.setEmail(ReadUtil.read());
                    System.out.println("→ Email actualizado");
                    editando = false;
                    break;
                case 6:
                    System.out.print("Nuevo nombre: ");
                    u.setNombre(ReadUtil.read());
                    System.out.print("Nuevo apellido paterno: ");
                    u.setPrimerApellido(ReadUtil.read());
                    System.out.print("Nuevo apellido materno: ");
                    u.setSegundoApellido(ReadUtil.read());
                    System.out.print("Nueva contraseña: ");
                    u.setPassword(ReadUtil.read());
                    System.out.print("Nuevo email: ");
                    u.setEmail(ReadUtil.read());
                    System.out.println("→ Todos los campos actualizados");
                    editando = false;
                    break;
                case 7:
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
