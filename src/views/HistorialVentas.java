package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HistorialVentas extends JFrame {

    public HistorialVentas(callbackString callback) {
        // Configuración del JFrame
        setTitle("Historial de Ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes externos

        // Tabla de ventas
        String[] columnas = {"ID de Venta", "Fecha", "Responsable", "Total"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable ventasTable = new JTable(modeloTabla);
        ventasTable.setRowHeight(30); // Altura de las filas
        ventasTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(ventasTable);
        tableScrollPane.setPreferredSize(new Dimension(760, 450));
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Agregar datos simulados (solo de ventas hechas por empleados)
        agregarDatosSimulados(modeloTabla);

        // Panel superior con título
        JLabel tituloLabel = new JLabel("Historial de Ventas", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        // Añadir componentes al panel principal
        mainPanel.add(tituloLabel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Configurar y mostrar la ventana
        add(mainPanel);
        setVisible(true);
    }

    // Método para agregar datos simulados (solo ventas realizadas por empleados)
    private void agregarDatosSimulados(DefaultTableModel modeloTabla) {
        // Solo ventas realizadas por empleados (se omiten las del sistema)
        Object[][] datos = {
                {"001", "2024-12-01", "Empleado A", 150.00},
                {"003", "2024-12-03", "Empleado B", 300.50},
                {"004", "2024-12-05", "Empleado A", 175.75}
        };
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }

    // Método principal para probar la interfaz
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HistorialVentas(null));
    }
}
