package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inventario extends JFrame {

    public Inventario(callbackString callback) {
        setTitle("Gestión de Inventario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 220));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnas = {"ID", "Nombre", "Cantidad", "Precio Unitario"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable productosTable = new JTable(modeloTabla);
        productosTable.setRowHeight(30);
        productosTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(productosTable);
        tableScrollPane.setPreferredSize(new Dimension(760, 450));
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 220));
        JButton agregarButton = new JButton("Agregar Producto");
        JButton modificarButton = new JButton("Modificar Producto");
        JButton eliminarButton = new JButton("Eliminar Producto");

        styleButton(agregarButton, new Color(34, 139, 34));
        styleButton(modificarButton, new Color(184, 134, 11));
        styleButton(eliminarButton, new Color(184, 70, 11));

        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        agregarDatosSimulados(modeloTabla);

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

        add(mainPanel);
        setVisible(true);
    }

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

    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(180, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventario(null));
    }
}
