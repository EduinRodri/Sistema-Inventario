package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;

public class AdminPanel extends JFrame {
    public AdminPanel(callbackString callback) {
        setTitle("Panel de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(160, 82, 45));

        JMenu logoMenu = new JMenu("");
        ImageIcon logoIcon = new ImageIcon("src/image/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        logoMenu.add(logoLabel);
        menuBar.add(logoMenu);

        JMenu menuUsuarios = new JMenu("Usuarios");
        JMenu menuFinanzas = new JMenu("Finanzas");
        JMenu menuProveedores = new JMenu("Proveedores");
        JMenu menuSeguridad = new JMenu("Seguridad");
        JMenu menuInventario = new JMenu("Inventario");
        JMenu menuCerrarSesion = new JMenu("Cerrar Sesión");

        menuUsuarios.setForeground(Color.WHITE);
        menuFinanzas.setForeground(Color.WHITE);
        menuProveedores.setForeground(Color.WHITE);
        menuSeguridad.setForeground(Color.WHITE);
        menuInventario.setForeground(Color.WHITE);
        menuCerrarSesion.setForeground(Color.WHITE);

        JMenuItem itemUsuarios = new JMenuItem("Gestión de Usuarios");
        JMenuItem itemFinanzas = new JMenuItem("Gestión de Finanzas");
        JMenuItem itemProveedores = new JMenuItem("Gestión de Proveedores");
        JMenuItem itemSeguridad = new JMenuItem("Gestión de Seguridad");
        JMenuItem itemInventario = new JMenuItem("Gestión de Inventario");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");

        menuUsuarios.add(itemUsuarios);
        menuFinanzas.add(itemFinanzas);
        menuProveedores.add(itemProveedores);
        menuSeguridad.add(itemSeguridad);
        menuInventario.add(itemInventario);
        menuCerrarSesion.add(itemCerrarSesion);

        menuBar.add(menuUsuarios);
        menuBar.add(menuFinanzas);
        menuBar.add(menuProveedores);
        menuBar.add(menuSeguridad);
        menuBar.add(menuInventario);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menuCerrarSesion);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 220));
        JLabel contentLabel = new JLabel("<html><h2>Bienvenido al Panel de Administrador</h2></html>", logoIcon, SwingConstants.CENTER);
        contentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        contentLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(contentLabel, BorderLayout.CENTER);

        itemUsuarios.addActionListener(e -> new Usuarios(null));
        itemFinanzas.addActionListener(e -> new Finanzas(null));
        itemProveedores.addActionListener(e -> contentLabel.setText("Gestión de Proveedores"));
        itemSeguridad.addActionListener(e -> contentLabel.setText("Gestión de Seguridad"));
        itemInventario.addActionListener(e -> new Inventario(null));

        itemCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (callback == null) {
                    System.exit(0);
                } else {
                    callback.ejecutar("cerrar");
                    this.dispose();
                }
            }
        });

        setJMenuBar(menuBar);
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminPanel(null));
    }
}
