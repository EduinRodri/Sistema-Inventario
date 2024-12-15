package views;

import callbacks.CallbackArryList;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;

public class Registrarse {
    ArrayList<String> params = new ArrayList<>();
    
    public Registrarse (CallbackArryList callback) {
        // Crear ventana principal
        JFrame frame = new JFrame("Registro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setResizable(false);

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        frame.setIconImage(icon);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        // Título
        JLabel titleLabel = new JLabel("Registrarse", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10)); // 6 filas para campos y checkbox
        formPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        // Campos de entrada
        JTextField userField = new JTextField();
        setPlaceholder(userField, "Usuario");

        JPasswordField passField = new JPasswordField();
        setPlaceholder(passField, "Contraseña");

        JPasswordField confirmPassField = new JPasswordField();
        setPlaceholder(confirmPassField, "Confirmar Contraseña");

        // Checkbox para mostrar contraseña
        JCheckBox showPasswordCheckBox = new JCheckBox("Mostrar Contraseña");
        showPasswordCheckBox.setBackground(new Color(245, 245, 220)); // Fondo beige
        showPasswordCheckBox.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        // Botón Registrarse
        JButton registerButton = new JButton("Registrarse");
        styleButton(registerButton, new Color(34, 139, 34)); // Botón verde

        // Agregar componentes al panel de formulario
        formPanel.add(userField);
        formPanel.add(passField);
        formPanel.add(confirmPassField);
        formPanel.add(showPasswordCheckBox);
        formPanel.add(registerButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Acción del checkbox para mostrar/ocultar contraseña
        showPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                char echoChar = showPasswordCheckBox.isSelected() ? (char) 0 : '●';
                passField.setEchoChar(echoChar);
                confirmPassField.setEchoChar(echoChar);
            }
        });

        // Acción del botón "Registrarse"
        registerButton.addActionListener(e -> {
            String usuario = userField.getText();
            String contrasena = new String(passField.getPassword());
            String confirmarContrasena = new String(confirmPassField.getPassword());

            // Validación básica
            if (usuario.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty() ||
                    usuario.equals("Usuario") || contrasena.equals("Contraseña") || confirmarContrasena.equals("Confirmar Contraseña")) {
                JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!contrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                params.add(usuario);
                params.add(contrasena);
                params.add("cliente");
                callback.ejecutar(params);
                frame.dispose();
            }
        });

        // Agregar el panel principal a la ventana
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Método para estilizar botones
    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 30));
    }

    // Método para agregar un placeholder a JTextField y JPasswordField
    private static void setPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private static void setPlaceholder(JPasswordField field, String placeholder) {
        field.setEchoChar((char) 0);
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('●'); // Ocultar caracteres
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setEchoChar((char) 0);
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    // Método principal para probar la clase de forma aislada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registrarse(null));
    }
}
