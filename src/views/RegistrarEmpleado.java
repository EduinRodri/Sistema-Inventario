package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;

public class RegistrarEmpleado extends JFrame {

    public RegistrarEmpleado(callbackString callback) {
        // Configuración del JFrame
        setTitle("Registrar Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta del título
        JLabel titleLabel = new JLabel("Registrar Nuevo Empleado", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos del formulario
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField(20);
        nameField.setToolTipText("Ingrese el nombre del empleado");

        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setToolTipText("Ingrese la contraseña del empleado");

        JLabel salaryLabel = new JLabel("Salario:");
        JTextField salaryField = new JTextField(20);
        salaryField.setToolTipText("Ingrese el salario del empleado");

        // Botones
        JButton registerButton = new JButton("Registrar");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        registerButton.addActionListener(e -> {
            String nombre = nameField.getText().trim();
            String contraseña = new String(passwordField.getPassword()).trim();
            String salario = salaryField.getText().trim();

            if (nombre.isEmpty() || contraseña.isEmpty() || salario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Empleado registrado con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cierra la ventana después del registro
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelButton.addActionListener(e -> dispose());

        // Añadir componentes al panel principal
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

        // Añadir el panel principal al JFrame
        add(mainPanel);

        setVisible(true);
    }

    // Método para probar la clase de forma aislada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrarEmpleado(null));
    }
}
