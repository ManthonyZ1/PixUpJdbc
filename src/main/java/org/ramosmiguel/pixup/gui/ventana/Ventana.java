package org.ramosmiguel.pixup.gui.ventana;

import org.ramosmiguel.pixup.negocio.Ejecutable;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame implements Ejecutable {
    private static Ventana instancia;
    private final CardLayout cards = new CardLayout();
    private final JPanel cardPanel = new JPanel(cards);

    private Ventana() {
        super("Pixup Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        initComponents();
    }

    public static Ventana getInstance() {
        if (instancia == null) instancia = new Ventana();
        return instancia;
    }

    private void initComponents() {
        // --- 1) PANEL PRINCIPAL ---
        JPanel mainMenu = new JPanel(new GridLayout(3, 1, 10, 10));
        mainMenu.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        JButton btnRegistrar = new JButton("1. Registrar Usuario");
        JButton btnDisco     = new JButton("2. Agregar Disco");
        JButton btnSalir     = new JButton("3. Salir");
        mainMenu.add(btnRegistrar);
        mainMenu.add(btnDisco);
        mainMenu.add(btnSalir);

        // --- 2) SUBMENÚ "Registrar Usuario" ---
        String[] optsUsuario = {
                "1. Estado",
                "2. Municipio",
                "3. Colonia",
                "4. Domicilio",
                "5. Tipo Domicilio",
                "6. Usuario",
                "7. Regresar"
        };
        JPanel usuarioMenu = buildMenuPanel("Registrar Usuario", optsUsuario);

        // --- 3) SUBMENÚ "Agregar Disco" ---
        String[] optsDisco = {
                "1. Disco",
                "2. Canción",
                "3. Artista",
                "4. Disquera",
                "5. Género Musical",
                "6. Regresar"
        };
        JPanel discoMenu = buildMenuPanel("Agregar Disco", optsDisco);

        // --- 4) AÑADIR AL CardLayout ---
        cardPanel.add(mainMenu,     "MAIN");
        cardPanel.add(usuarioMenu,  "USUARIO");
        cardPanel.add(discoMenu,    "DISCO");
        add(cardPanel);

        // --- 5) NAVEGACIÓN entre menús ---
        btnRegistrar.addActionListener(e -> cards.show(cardPanel, "USUARIO"));
        btnDisco    .addActionListener(e -> cards.show(cardPanel, "DISCO"));
        btnSalir    .addActionListener(e -> System.exit(0));

        // --- 6) ASIGNAR ActionListeners a botones de SUBMENÚ USUARIO ---
        JPanel btnsUsu = (JPanel) usuarioMenu.getComponent(1);
        ((JButton)btnsUsu.getComponent(0)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Estados"));
        ((JButton)btnsUsu.getComponent(1)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Municipios"));
        ((JButton)btnsUsu.getComponent(2)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Colonias"));
        ((JButton)btnsUsu.getComponent(3)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Domicilios"));
        ((JButton)btnsUsu.getComponent(4)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Tipos de Domicilio"));
        ((JButton)btnsUsu.getComponent(5)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Usuarios"));
        ((JButton)btnsUsu.getComponent(6)).addActionListener(e ->
                cards.show(cardPanel, "MAIN"));  // Regresar

        // --- 7) ASIGNAR ActionListeners a botones de SUBMENÚ DISCO ---
        JPanel btnsDisc = (JPanel) discoMenu.getComponent(1);
        ((JButton)btnsDisc.getComponent(0)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Discos"));
        ((JButton)btnsDisc.getComponent(1)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Canciones"));
        ((JButton)btnsDisc.getComponent(2)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Artistas"));
        ((JButton)btnsDisc.getComponent(3)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Disqueras"));
        ((JButton)btnsDisc.getComponent(4)).addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir gestión de Géneros Musicales"));
        ((JButton)btnsDisc.getComponent(5)).addActionListener(e ->
                cards.show(cardPanel, "MAIN"));  // Regresar
    }

    /**
     * Construye un panel con título y botones según las etiquetas.
     */
    private JPanel buildMenuPanel(String titulo, String[] etiquetas) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setFont(lbl.getFont().deriveFont(20f));
        panel.add(lbl, BorderLayout.NORTH);

        JPanel btns = new JPanel(new GridLayout(etiquetas.length, 1, 5, 5));
        btns.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));
        for (String et : etiquetas) {
            btns.add(new JButton(et));
        }
        panel.add(btns, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Ejecutable.run() arranca la ventana en el hilo de Swing.
     */
    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> getInstance().setVisible(true));
    }

    /** Ya no usamos esta bandera en Swing. */
    @Override
    public void setFlag(boolean flag) { }
}
