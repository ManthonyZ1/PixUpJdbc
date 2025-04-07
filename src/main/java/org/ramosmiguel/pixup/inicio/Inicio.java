package org.ramosmiguel.pixup.inicio;

import org.ramosmiguel.pixup.gui.ConsolaVentana;

public class Inicio
{
    public static void main( String[] args )
    {
        System.out.println( "Inicio PixUp" );
        ConsolaVentana.getInstance( ).run( );
        System.out.println( "Termino PixUp" );
    }
}
