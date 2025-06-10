package org.ramosmiguel.pixup.gui.consola;

import org.ramosmiguel.pixup.model.Notificacion;
import org.ramosmiguel.pixup.util.ReadUtil;

public class NotificacionCatalogo extends Catalogos<Notificacion> {
    private static NotificacionCatalogo instance;
    private NotificacionCatalogo() { super(); }
    public static NotificacionCatalogo getInstance() {
        if (instance == null) instance = new NotificacionCatalogo();
        return instance;
    }
    @Override
    public Notificacion newT() {
        return new Notificacion();
    }

    @Override
    public boolean processNewT(Notificacion n) {
        System.out.println("Fecha (yyyy-MM-dd HH:mm:ss):"); n.setFechaNotificacion(ReadUtil.read());
        System.out.println("ID Usuario:"); n.setUsuario_id(ReadUtil.readInt());
        System.out.println("ID Tipo Notificación:"); n.setId_tipo_notifacion(ReadUtil.readInt());
        return true;
    }

    @Override
    public void processEditT(Notificacion n) {
        System.out.println("Nueva fecha:"); n.setFechaNotificacion(ReadUtil.read());
    }
}

