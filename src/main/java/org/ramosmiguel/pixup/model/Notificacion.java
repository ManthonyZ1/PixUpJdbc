package org.ramosmiguel.pixup.model;

public class Notificacion extends Catalogo{

    private String fechaNotificacion;
    private Integer id_usuario;
    private Integer id_tipo_notifacion;

    public Notificacion() {
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }
    public Integer getUsuario_id() {
        return id_usuario;
    }
    public Integer getId_tipo_notifacion() {
        return id_tipo_notifacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }
    public void setUsuario_id(Integer usuario_id) {
        this.id_usuario = usuario_id;
    }
    public void setId_tipo_notifacion(Integer id_tipo_notifacion) {
        this.id_tipo_notifacion = id_tipo_notifacion;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "fechaNotificacion='" + fechaNotificacion + '\'' +
                ", usuario_id=" + id_usuario +
                ", id_tipo_notifacion=" + id_tipo_notifacion +
                ", id=" + id +
                '}';
    }
}
