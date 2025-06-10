package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.MunicipioJdbc;
import org.ramosmiguel.pixup.jdbc.impl.MunicipioJdbcImpl;
import org.ramosmiguel.pixup.model.Municipio;
import org.ramosmiguel.pixup.util.ReadUtil;

public class MunicipioCatalogo extends Catalogos<Municipio> {
    private final MunicipioJdbc dao = MunicipioJdbcImpl.getInstance();
    private static MunicipioCatalogo instance;


    private MunicipioCatalogo() {
        super();
        this.list = dao.findAll();
    }



    public static MunicipioCatalogo getInstance() {
        if (instance == null) instance = new MunicipioCatalogo();
        return instance;
    }
    @Override
    public Municipio newT() {
        return new Municipio();
    }

    @Override
    public boolean processNewT(Municipio m) {

        System.out.println("Nombre:"); m.setMunicipio(ReadUtil.read());
        System.out.println("ID Estado:"); m.setId_estado(ReadUtil.readInt());
        return true;
    }

    @Override
    public void processEditT(Municipio m) {
        System.out.println("ID del municipio: "); m.getId();
        System.out.println("Nuevo nombre:"); m.setMunicipio(ReadUtil.read());
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

    @Override
    public void print() {
        this.list = dao.findAll();
        super.print();
    }
}