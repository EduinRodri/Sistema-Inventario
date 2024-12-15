package views;

import java.awt.*;
import javax.swing.*;

import callbacks.callbackString;

public class AdminPanel extends JFrame {
    // Constructor
    public AdminPanel(callbackString callback) {
        // Configurar ventana principal
        setTitle("Panel de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(70, 130, 180)); // Color azul

        // Opciones del menú
        JMenu menuUsuarios = new JMenu("Usuarios");
        JMenu menuFinanzas = new JMenu("Finanzas");
        JMenu menuProveedores = new JMenu("Proveedores");
        JMenu menuSeguridad = new JMenu("Seguridad");
        JMenu menuInventario = new JMenu("Inventario");
        JMenu menuCerrarSesion = new JMenu("Cerrar Sesión");

        // Crear items del menú
        JMenuItem itemUsuarios = new JMenuItem("Gestión de Usuarios");
        JMenuItem itemFinanzas = new JMenuItem("Gestión de Finanzas");
        JMenuItem itemProveedores = new JMenuItem("Gestión de Proveedores");
        JMenuItem itemSeguridad = new JMenuItem("Gestión de Seguridad");
        JMenuItem itemInventario = new JMenuItem("Gestión de Inventario");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");

        // Agregar items a los menús
        menuUsuarios.add(itemUsuarios);
        menuFinanzas.add(itemFinanzas);
        menuProveedores.add(itemProveedores);
        menuSeguridad.add(itemSeguridad);
        menuInventario.add(itemInventario);
        menuCerrarSesion.add(itemCerrarSesion);

        // Agregar menús a la barra de menú
        menuBar.add(menuUsuarios);
        menuBar.add(menuFinanzas);
        menuBar.add(menuProveedores);
        menuBar.add(menuSeguridad);
        menuBar.add(menuInventario);
        menuBar.add(Box.createHorizontalGlue()); // Empuja "Cerrar Sesión" a la derecha
        menuBar.add(menuCerrarSesion);

        // Panel principal donde se mostrará el contenido
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel contentLabel = new JLabel("Seleccione una opción del menú", SwingConstants.CENTER);
        contentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(contentLabel, BorderLayout.CENTER);

        // Acciones para los items del menú
        itemUsuarios.addActionListener(e -> contentLabel.setText("Gestión de Usuarios"));
        itemFinanzas.addActionListener(e -> contentLabel.setText("Gestión de Finanzas"));
        itemProveedores.addActionListener(e -> contentLabel.setText("Gestión de Proveedores"));
        itemSeguridad.addActionListener(e -> contentLabel.setText("Gestión de Seguridad"));
        itemInventario.addActionListener(e -> contentLabel.setText("Gestión de Inventario"));

        // Acción para "Cerrar Sesión"
        itemCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                //JOptionPane.showMessageDialog(this, "Sesión cerrada. Hasta pronto.", JOptionPane.INFORMATION_MESSAGE);
                if (callback == null) {
                    System.exit(0);
                } else {
                    callback.ejecutar("cerrar");
                }
            }
        });

        // Configurar la ventana con la barra de menú y el panel principal
        setJMenuBar(menuBar);
        add(mainPanel);
        setVisible(true);
    }

    // Método para probar la clase de forma aislada (opcional)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminPanel(null));
    }
}
