package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;

public class Empleado extends JFrame {

    public Empleado(callbackString callback) {
        setTitle("Panel del Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 220));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(160, 82, 45));

        JMenu opcionesMenu = new JMenu("Opciones");
        opcionesMenu.setForeground(Color.WHITE);

        JMenuItem cerrarSesionMenuItem = new JMenuItem("Cerrar Sesión");
        JMenuItem historialVentasMenuItem = new JMenuItem("Historial de Ventas");
        JMenuItem inventarioMenuItem = new JMenuItem("Inventario");
        JMenuItem venderMenuItem = new JMenuItem("Vender");

        opcionesMenu.add(cerrarSesionMenuItem);
        opcionesMenu.add(historialVentasMenuItem);
        opcionesMenu.add(inventarioMenuItem);
        opcionesMenu.add(venderMenuItem);

        menuBar.add(opcionesMenu);
        setJMenuBar(menuBar);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(245, 245, 220));

        JLabel bienvenidaLabel = new JLabel("<html><h2>Bienvenido al Panel del Empleado</h2></html>");
        bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bienvenidaLabel.setForeground(new Color(139, 69, 19));

        ImageIcon logoIcon = new ImageIcon("src/image/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        contentPanel.add(logoLabel, new GridBagConstraints());
        contentPanel.add(bienvenidaLabel, new GridBagConstraints());

        historialVentasMenuItem.addActionListener(e -> mostrarHistorialVentas());
        inventarioMenuItem.addActionListener(e -> mostrarInventario());
        venderMenuItem.addActionListener(e -> iniciarVenta());

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);

        cerrarSesionMenuItem.addActionListener(e -> {
            if (callback == null) {
                System.exit(0);
            } else {
                callback.ejecutar("cerrar");
                this.dispose();
            }
        });
        setVisible(true);
    }

    private void mostrarHistorialVentas() {
        HistorialVentas ventas = new HistorialVentas(null);
    }

    private void mostrarInventario() {
        Inventario inventario = new Inventario(null);
    }

    private void iniciarVenta() {
        Vender vender = new Vender(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Empleado(null));
    }
}
