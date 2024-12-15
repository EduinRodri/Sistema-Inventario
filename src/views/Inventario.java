package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inventario extends JFrame {

    public Inventario(callbackString callback) {
        // Configuración del JFrame
        setTitle("Gestión de Inventario");
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

        // Tabla de productos
        String[] columnas = {"ID", "Nombre", "Cantidad", "Precio Unitario"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable productosTable = new JTable(modeloTabla);
        productosTable.setRowHeight(30); // Altura de las filas
        productosTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(productosTable);
        tableScrollPane.setPreferredSize(new Dimension(760, 450));
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Panel inferior: Botones de acción
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        JButton agregarButton = new JButton("Agregar Producto");
        JButton modificarButton = new JButton("Modificar Producto");
        JButton eliminarButton = new JButton("Eliminar Producto");

        // Estilizar botones
        styleButton(agregarButton, new Color(34, 139, 34)); // Botón verde
        styleButton(modificarButton, new Color(184, 134, 11)); // Botón marrón
        styleButton(eliminarButton, new Color(184, 70, 11)); // Botón rojo suave

        // Agregar botones al panel
        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);

        // Añadir componentes al panel principal
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Simulación de datos iniciales
        agregarDatosSimulados(modeloTabla);

        // Acción: Agregar Producto
        agregarButton.addActionListener(e -> {
            JTextField idField = new JTextField();
            JTextField nombreField = new JTextField();
            JTextField cantidadField = new JTextField();
            JTextField precioField = new JTextField();

            Object[] mensaje = {
                "ID:", idField,
                "Nombre:", nombreField,
                "Cantidad:", cantidadField,
                "Precio Unitario:", precioField
            };

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                try {
                    String id = idField.getText().trim();
                    String nombre = nombreField.getText().trim();
                    int cantidad = Integer.parseInt(cantidadField.getText().trim());
                    double precio = Double.parseDouble(precioField.getText().trim());
                    modeloTabla.addRow(new Object[]{id, nombre, cantidad, precio});
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese datos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción: Modificar Producto
        modificarButton.addActionListener(e -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JTextField idField = new JTextField(productosTable.getValueAt(selectedRow, 0).toString());
            JTextField nombreField = new JTextField(productosTable.getValueAt(selectedRow, 1).toString());
            JTextField cantidadField = new JTextField(productosTable.getValueAt(selectedRow, 2).toString());
            JTextField precioField = new JTextField(productosTable.getValueAt(selectedRow, 3).toString());

            Object[] mensaje = {
                "ID:", idField,
                "Nombre:", nombreField,
                "Cantidad:", cantidadField,
                "Precio Unitario:", precioField
            };

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Modificar Producto", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                try {
                    productosTable.setValueAt(idField.getText().trim(), selectedRow, 0);
                    productosTable.setValueAt(nombreField.getText().trim(), selectedRow, 1);
                    productosTable.setValueAt(Integer.parseInt(cantidadField.getText().trim()), selectedRow, 2);
                    productosTable.setValueAt(Double.parseDouble(precioField.getText().trim()), selectedRow, 3);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese datos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción: Eliminar Producto
        eliminarButton.addActionListener(e -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    modeloTabla.removeRow(selectedRow);
                }
            }
        });

        // Añadir el panel principal al JFrame
        add(mainPanel);
        setVisible(true);
    }

    // Método para agregar datos simulados
    private void agregarDatosSimulados(DefaultTableModel modeloTabla) {
        Object[][] datos = {
                {"001", "Producto A", 50, 10.00},
                {"002", "Producto B", 30, 20.00},
                {"003", "Producto C", 70, 15.00}
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
        button.setPreferredSize(new Dimension(180, 40));
    }

    // Método para probar la clase de forma aislada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventario(null));
    }
}
