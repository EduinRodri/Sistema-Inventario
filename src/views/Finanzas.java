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

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Panel superior: Resumen general
        JPanel resumenPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        resumenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel ingresosLabel = new JLabel("<html><b>Total Ingresos:</b> $0.00</html>", SwingConstants.CENTER);
        JLabel egresosLabel = new JLabel("<html><b>Total Egresos:</b> $0.00</html>", SwingConstants.CENTER);
        JLabel balanceLabel = new JLabel("<html><b>Balance:</b> $0.00</html>", SwingConstants.CENTER);

        ingresosLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        egresosLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        resumenPanel.add(ingresosLabel);
        resumenPanel.add(egresosLabel);
        resumenPanel.add(balanceLabel);

        // Panel central: Tabla de transacciones
        String[] columnas = {"Fecha", "Origen", "Descripción", "Monto"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable transaccionesTable = new JTable(modeloTabla);
        transaccionesTable.setFillsViewportHeight(true);

        JScrollPane tableScrollPane = new JScrollPane(transaccionesTable);

        // Panel inferior: Botón de cerrar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cerrarButton.addActionListener(e -> dispose());

        buttonPanel.add(cerrarButton);

        // Simulación de datos en la tabla
        agregarDatosSimulados(modeloTabla);

        // Añadir los paneles al mainPanel
        mainPanel.add(resumenPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir el panel principal al JFrame
        add(mainPanel);

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

    // Método para probar la clase de forma aislada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Finanzas(null));
    }
}
