package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import callbacks.callbackString;

public class Vender extends JFrame {
    
    // Elementos de la interfaz
    private JComboBox<String> comboProductos;
    private JTextField cantidadField, precioUnitarioField, totalVentaField;
    private JButton registrarVentaButton;

    public Vender(callbackString callback) {
        // Configuración del JFrame
        setTitle("Vender Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        // Panel principal con GridBagLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen alrededor de cada componente
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        // Crear los componentes
        JLabel productoLabel = new JLabel("Seleccionar Producto:");
        comboProductos = new JComboBox<>(new String[] {"Producto A", "Producto B", "Producto C"});
        productoLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        comboProductos.setBackground(Color.WHITE);
        comboProductos.setForeground(Color.BLACK);
        
        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadField = new JTextField(10);
        cantidadLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        JLabel precioUnitarioLabel = new JLabel("Precio Unitario:");
        precioUnitarioField = new JTextField(10);
        precioUnitarioField.setEditable(false);  // No editable, solo visualización
        precioUnitarioLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        JLabel totalLabel = new JLabel("Total de la Venta:");
        totalVentaField = new JTextField(10);
        totalVentaField.setEditable(false);  // No editable, se calcula automáticamente
        totalLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        registrarVentaButton = new JButton("Registrar Venta");
        styleButton(registrarVentaButton, new Color(34, 139, 34)); // Botón verde

        // Añadir componentes al panel con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(productoLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(comboProductos, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(cantidadLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(cantidadField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(precioUnitarioLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(precioUnitarioField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(totalLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(totalVentaField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(registrarVentaButton, gbc);

        // Configurar la acción del botón de registrar venta
        registrarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        // Panel para el título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        JLabel titleLabel = new JLabel("Registrar Venta", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        titlePanel.add(titleLabel);

        // Añadir el título y el panel principal
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);

        // Inicializar precio unitario basado en el producto seleccionado
        actualizarPrecioUnitario();
        comboProductos.addActionListener(e -> actualizarPrecioUnitario());
    }

    // Método para actualizar el precio unitario dependiendo del producto seleccionado
    private void actualizarPrecioUnitario() {
        String productoSeleccionado = (String) comboProductos.getSelectedItem();
        switch (productoSeleccionado) {
            case "Producto A":
                precioUnitarioField.setText("50.00");
                break;
            case "Producto B":
                precioUnitarioField.setText("30.00");
                break;
            case "Producto C":
                precioUnitarioField.setText("20.00");
                break;
        }
        calcularTotalVenta();
    }

    // Método para calcular el total de la venta
    private void calcularTotalVenta() {
        try {
            int cantidad = Integer.parseInt(cantidadField.getText());
            double precioUnitario = Double.parseDouble(precioUnitarioField.getText());
            double total = cantidad * precioUnitario;
            totalVentaField.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            totalVentaField.setText("0.00");
        }
    }

    // Método para registrar la venta (aquí solo mostramos un mensaje de confirmación)
    private void registrarVenta() {
        String producto = (String) comboProductos.getSelectedItem();
        String cantidad = cantidadField.getText();
        String total = totalVentaField.getText();

        // Mostrar confirmación (en un sistema real, aquí se guardaría la venta)
        JOptionPane.showMessageDialog(this, 
            "Venta registrada:\nProducto: " + producto +
            "\nCantidad: " + cantidad +
            "\nTotal: $" + total, 
            "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar campos después de registrar la venta
        cantidadField.setText("");
        totalVentaField.setText("0.00");
    }

    // Método para estilizar botones
    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(180, 40));
    }

    // Método principal para probar la interfaz
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Vender(null));
    }
}
