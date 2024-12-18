package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;

public class RegistrarEmpleado extends JFrame {

    public RegistrarEmpleado(callbackString callback) {
        setTitle("Registrar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Registrar Nuevo Empleado", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(139, 69, 19));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setForeground(new Color(139, 69, 19));
        JTextField nameField = new JTextField(20);
        nameField.setToolTipText("Ingrese el nombre del empleado");

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(new Color(139, 69, 19));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setToolTipText("Ingrese la contraseña del empleado");

        JLabel salaryLabel = new JLabel("Salario:");
        salaryLabel.setForeground(new Color(139, 69, 19));
        JTextField salaryField = new JTextField(20);
        salaryField.setToolTipText("Ingrese el salario del empleado");

        JButton registerButton = new JButton("Registrar");
        styleButton(registerButton, new Color(34, 139, 34));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        registerButton.addActionListener(e -> {
            String nombre = nameField.getText().trim();
            String contraseña = new String(passwordField.getPassword()).trim();
            String salario = salaryField.getText().trim();

            if (nombre.isEmpty() || contraseña.isEmpty() || salario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Empleado registrado con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        styleButton(cancelButton, new Color(184, 70, 11));
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelButton.addActionListener(e -> dispose());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(salaryLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbc);

        gbc.gridx = 1;
        mainPanel.add(cancelButton, gbc);

        add(mainPanel);

        setVisible(true);
    }

    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 30));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrarEmpleado(null));
    }
}
