package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import callbacks.callbackString;

public class Vender extends JFrame {
    
    private JComboBox<String> comboProductos;
    private JTextField cantidadField, precioUnitarioField, totalVentaField;
    private JButton registrarVentaButton;

    public Vender(callbackString callback) {
        setTitle("Vender Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.setBackground(new Color(245, 245, 220));

        JLabel productoLabel = new JLabel("Seleccionar Producto:");
        comboProductos = new JComboBox<>(new String[] {"Producto A", "Producto B", "Producto C"});
        productoLabel.setForeground(new Color(139, 69, 19));
        comboProductos.setBackground(Color.WHITE);
        comboProductos.setForeground(Color.BLACK);
        
        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadField = new JTextField(10);
        cantidadLabel.setForeground(new Color(139, 69, 19));

        JLabel precioUnitarioLabel = new JLabel("Precio Unitario:");
        precioUnitarioField = new JTextField(10);
        precioUnitarioField.setEditable(false);
        precioUnitarioLabel.setForeground(new Color(139, 69, 19));

        JLabel totalLabel = new JLabel("Total de la Venta:");
        totalVentaField = new JTextField(10);
        totalVentaField.setEditable(false);
        totalLabel.setForeground(new Color(139, 69, 19));

        registrarVentaButton = new JButton("Registrar Venta");
        styleButton(registrarVentaButton, new Color(34, 139, 34));

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

        registrarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(245, 245, 220));
        JLabel titleLabel = new JLabel("Registrar Venta", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19));
        titlePanel.add(titleLabel);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);

        actualizarPrecioUnitario();
        comboProductos.addActionListener(e -> actualizarPrecioUnitario());
    }

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

    private void registrarVenta() {
        String producto = (String) comboProductos.getSelectedItem();
        String cantidad = cantidadField.getText();
        String total = totalVentaField.getText();

        JOptionPane.showMessageDialog(this, 
            "Venta registrada:\nProducto: " + producto +
            "\nCantidad: " + cantidad +
            "\nTotal: $" + total, 
            "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);

        cantidadField.setText("");
        totalVentaField.setText("0.00");
    }

    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(180, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Vender(null));
    }
}
