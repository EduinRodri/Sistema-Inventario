package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoDeCompras {

    public CarritoDeCompras() {
        JFrame frame = new JFrame("Carrito de Compras");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(20, 20));

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        frame.setIconImage(icon);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 220));
        frame.add(mainPanel, BorderLayout.CENTER);

        String[] columnNames = {"Producto", "Precio", "Cantidad", "Subtotal"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setFillsViewportHeight(true);

        tableModel.addRow(new Object[]{"Pan Integral", 2.5, 2, 5.0});
        tableModel.addRow(new Object[]{"Pan Dulce", 1.75, 3, 5.25});
        tableModel.addRow(new Object[]{"Croissant", 3.0, 1, 3.0});

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        mainPanel.add(tableScroll, BorderLayout.CENTER);

        JLabel totalLabel = new JLabel("Total: $13.25", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(139, 69, 19));
        updateTotal(tableModel, totalLabel);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        bottomPanel.setBackground(new Color(245, 245, 220));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 220));

        JButton deleteButton = new JButton("Eliminar Producto");
        styleButton(deleteButton, new Color(184, 70, 11));

        JButton checkoutButton = new JButton("Finalizar Compra");
        styleButton(checkoutButton, new Color(34, 139, 34));

        JButton cancelButton = new JButton("Cancelar");
        styleButton(cancelButton, new Color(105, 105, 105));

        buttonPanel.add(deleteButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(cancelButton);

        bottomPanel.add(totalLabel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                updateTotal(tableModel, totalLabel);
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un producto para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "¡Compra finalizada! Gracias por su preferencia.", "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
        });

        cancelButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(frame, "¿Está seguro que desea cancelar?", "Cancelar Compra", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static void updateTotal(DefaultTableModel tableModel, JLabel totalLabel) {
        double total = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            double price = (double) tableModel.getValueAt(i, 1);
            int quantity = (int) tableModel.getValueAt(i, 2);
            total += price * quantity;
        }
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(180, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarritoDeCompras::new);
    }
}
