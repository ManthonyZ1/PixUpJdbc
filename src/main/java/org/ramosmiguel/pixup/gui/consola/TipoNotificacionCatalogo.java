package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.jdbc.TipoDomicilioJdbc;
import org.ramosmiguel.pixup.jdbc.impl.TipoDomicilioJdbcImpl;
import org.ramosmiguel.pixup.model.TipoNotificacion;
import org.ramosmiguel.pixup.util.ReadUtil;

public class TipoNotificacionCatalogo extends Catalogos<TipoNotificacion> {
    private final TipoDomicilioJdbc dao = TipoDomicilioJdbcImpl.getInstance();
    private static TipoNotificacionCatalogo instance;

    private TipoNotificacionCatalogo()
    {
        super();

    }

    public static TipoNotificacionCatalogo getInstance() {
        if (instance == null) instance = new TipoNotificacionCatalogo();
        return instance;
    }
    @Override
    public TipoNotificacion newT() {
        return new TipoNotificacion();
    }

    @Override
    public boolean processNewT(TipoNotificacion t) {
        System.out.println("Descripción:"); t.setDescripcion(ReadUtil.read());
        System.out.println("Ruta plantilla:"); t.setRutaPlantilla(ReadUtil.read());
        return true;
    }

    @Override
    public void processEditT(TipoNotificacion t) {
        System.out.println("Nueva descripción:"); t.setDescripcion(ReadUtil.read());
    }
}