package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;

public class Empleado extends JFrame {

    public Empleado(callbackString callback) {
        // Configuración del JFrame
        setTitle("Panel del Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        // Crear barra de navegación
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(160, 82, 45)); // Fondo marrón

        JMenu opcionesMenu = new JMenu("Opciones");
        opcionesMenu.setForeground(Color.WHITE);

        JMenuItem cerrarSesionMenuItem = new JMenuItem("Cerrar Sesión");
        JMenuItem historialVentasMenuItem = new JMenuItem("Historial de Ventas");
        JMenuItem inventarioMenuItem = new JMenuItem("Inventario");
        JMenuItem venderMenuItem = new JMenuItem("Vender");

        // Agregar elementos al menú
        opcionesMenu.add(cerrarSesionMenuItem);
        opcionesMenu.add(historialVentasMenuItem);
        opcionesMenu.add(inventarioMenuItem);
        opcionesMenu.add(venderMenuItem);

        menuBar.add(opcionesMenu);
        setJMenuBar(menuBar);

        // Panel central
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        JLabel bienvenidaLabel = new JLabel("<html><h2>Bienvenido al Panel del Empleado</h2></html>");
        bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bienvenidaLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        // Cargar y agregar el logo
        ImageIcon logoIcon = new ImageIcon("src/image/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        contentPanel.add(logoLabel, new GridBagConstraints());
        contentPanel.add(bienvenidaLabel, new GridBagConstraints());

        // Acción de botones del menú
        cerrarSesionMenuItem.addActionListener(e -> cerrarSesion());
        historialVentasMenuItem.addActionListener(e -> mostrarHistorialVentas());
        inventarioMenuItem.addActionListener(e -> mostrarInventario());
        venderMenuItem.addActionListener(e -> iniciarVenta());

        // Agregar el panel principal
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

    // Método: Cerrar Sesión
    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Cierra la ventana actual
            // Aquí puedes redirigir a la pantalla de login si es necesario
        }
    }

    // Método: Mostrar Historial de Ventas
    private void mostrarHistorialVentas() {
        JOptionPane.showMessageDialog(this, "Función para mostrar el historial de ventas (por implementar).", "Historial de Ventas", JOptionPane.INFORMATION_MESSAGE);
        // Aquí puedes abrir una nueva ventana para el historial de ventas
    }

    // Método: Mostrar Inventario
    private void mostrarInventario() {
        JOptionPane.showMessageDialog(this, "Función para mostrar el inventario (por implementar).", "Inventario", JOptionPane.INFORMATION_MESSAGE);
        // Aquí puedes abrir una nueva ventana para el inventario
    }

    // Método: Iniciar Venta
    private void iniciarVenta() {
        JOptionPane.showMessageDialog(this, "Función para iniciar una venta (por implementar).", "Vender", JOptionPane.INFORMATION_MESSAGE);
        // Aquí puedes abrir una ventana para gestionar ventas
    }

    // Método principal para probar la interfaz
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Empleado(null));
    }
}
