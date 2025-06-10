package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.TipoDomicilioJdbc;
import org.ramosmiguel.pixup.jdbc.impl.TipoDomicilioJdbcImpl;
import org.ramosmiguel.pixup.model.TipoDomicilio;
import org.ramosmiguel.pixup.util.ReadUtil;

public class TipoDomicilioCatalogo extends Catalogos<TipoDomicilio> {
    private final TipoDomicilioJdbc dao = TipoDomicilioJdbcImpl.getInstance();
    private static TipoDomicilioCatalogo instance;

    private TipoDomicilioCatalogo() {
        super();
        this.list = dao.findAll();
    }

    public static TipoDomicilioCatalogo getInstance() {
        if (instance == null) instance = new TipoDomicilioCatalogo();
        return instance;
    }

    @Override
    public TipoDomicilio newT() {
        return new TipoDomicilio();
    }

    @Override
    public boolean processNewT(TipoDomicilio t) {
        System.out.println("Descripción:");
        t.setDescripcion(ReadUtil.read());

        // Mostrar domicilios disponibles
        System.out.println("Lista de domicilios registrados:");
        DomicilioCatalogo.getInstance().getList().forEach(d ->
                System.out.println(d.getId() + " - " + d.getCalle())
        );

        System.out.print("Seleccione el ID del domicilio asociado: ");
        t.setTblDomicilioId(ReadUtil.readInt());

        return true;
    }

    @Override
    public void processEditT(TipoDomicilio t) {
        System.out.println("ID actual: " + t.getId());
        System.out.println("Descripción actual: " + t.getDescripcion());
        System.out.println("ID de domicilio asociado: " + t.getTblDomicilioId());

        System.out.println("1) Editar descripción");
        System.out.println("2) Cambiar ID de domicilio asociado");
        System.out.println("3) Editar ambos");
        System.out.println("4) Salir sin cambios");

        int opcion = ReadUtil.readInt();
        switch (opcion) {
            case 1:
                System.out.println("Nueva descripción:");
                t.setDescripcion(ReadUtil.read());
                break;
            case 2:
                System.out.println("Nuevo ID de domicilio:");
                t.setTblDomicilioId(ReadUtil.readInt());
                break;
            case 3:
                System.out.println("Nueva descripción:");
                t.setDescripcion(ReadUtil.read());
                System.out.println("Nuevo ID de domicilio:");
                t.setTblDomicilioId(ReadUtil.readInt());
                break;
            case 4:
                System.out.println("No se realizaron cambios.");
                break;
            default:
                System.out.println("Opción inválida.");
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
