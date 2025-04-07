package org.ramosmiguel.pixup.gui.ventana;

import org.ramosmiguel.pixup.negocio.Ejecutable;

public class Ventana implements Ejecutable
{
    public static Ventana ventana;
    private boolean flag;
    private Ventana()
    {
    }

    public static Ventana getInstance( )
    {
        if(ventana==null)
        {
            ventana = new Ventana();
        }
        return ventana;
    }

    @Override
    public void run()
    {

    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
