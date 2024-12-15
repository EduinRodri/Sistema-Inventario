package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Finanzas extends JFrame {

    public Finanzas(callbackString callback) {
        // Configuración del JFrame
        setTitle("Gestión de Finanzas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes externos

        // Panel superior: Resumen general
        JPanel resumenPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        resumenPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        resumenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel ingresosLabel = new JLabel("<html><b>Total Ingresos:</b> $0.00</html>", SwingConstants.CENTER);
        JLabel egresosLabel = new JLabel("<html><b>Total Egresos:</b> $0.00</html>", SwingConstants.CENTER);
        JLabel balanceLabel = new JLabel("<html><b>Balance:</b> $0.00</html>", SwingConstants.CENTER);

        ingresosLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ingresosLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        egresosLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        egresosLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        resumenPanel.add(ingresosLabel);
        resumenPanel.add(egresosLabel);
        resumenPanel.add(balanceLabel);

        // Panel central: Tabla de transacciones
        String[] columnas = {"Fecha", "Origen", "Descripción", "Monto"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable transaccionesTable = new JTable(modeloTabla);
        transaccionesTable.setRowHeight(30); // Altura de las filas
        transaccionesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(transaccionesTable);
        tableScrollPane.setPreferredSize(new Dimension(760, 350));
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Simulación de datos en la tabla
        agregarDatosSimulados(modeloTabla);

        // Panel inferior: Botón de cerrar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton cerrarButton = new JButton("Cerrar");
        styleButton(cerrarButton, new Color(184, 134, 11)); // Botón marrón
        cerrarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cerrarButton.addActionListener(e -> dispose());

        buttonPanel.add(cerrarButton);

        // Añadir los paneles al mainPanel
        mainPanel.add(resumenPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir el panel principal al JFrame
        add(mainPanel);

        cerrarButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }

    // Método para agregar datos simulados
    private void agregarDatosSimulados(DefaultTableModel modeloTabla) {
        Object[][] datos = {
                {"2024-12-01", "Empleado", "Venta de producto A", 150.00},
                {"2024-12-02", "Sistema", "Venta en línea", 300.00},
                {"2024-12-03", "Empleado", "Devolución de producto B", -50.00},
                {"2024-12-04", "Sistema", "Venta de producto C", 200.00}
        };

        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }

    // Método para estilizar botones
    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 40));
    }

    // Método para probar la clase de forma aislada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Finanzas(null));
    }
}
