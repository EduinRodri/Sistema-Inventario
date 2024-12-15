package views;

import javax.swing.*;

import callbacks.CallbackArryList;
import callbacks.callbackString;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class LoginApp {
    private String nombre = "";
    private String pass = "";
    private boolean aceptado = false;
    private CallbackArryList TheCallback;
    public JFrame Frame;

    public LoginApp(callbackString callback) {
        // Crear ventana principal
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setResizable(false);

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        frame.setIconImage(icon);

        this.Frame = frame;

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes externos
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        // Título
        JLabel titleLabel = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel central con campos de texto
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 filas, espaciado 10px
        formPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        JTextField userField = new JTextField();
        setPlaceholder(userField, "Usuario");

        JPasswordField passField = new JPasswordField();
        setPlaceholder(passField, "Contraseña");

        formPanel.add(userField);
        formPanel.add(passField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(245, 245, 220)); // Fondo beige

        JButton loginButton = new JButton("Iniciar Sesión");
        styleButton(loginButton, new Color(184, 134, 11)); // Botón marrón
        buttonPanel.add(loginButton, BorderLayout.CENTER);

        // Pregunta de registro
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        JLabel registerLabel = new JLabel("¿No se ha registrado?");
        registerLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        JButton registerButton = new JButton("Registrarse");
        styleButton(registerButton, new Color(70, 130, 180)); // Botón azul

        registerPanel.add(registerLabel);
        registerPanel.add(registerButton);
        buttonPanel.add(registerPanel, BorderLayout.SOUTH);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Acción del botón "Iniciar Sesión"
        loginButton.addActionListener(e -> {
            String usuario = userField.getText();
            String contrasena = new String(passField.getPassword());

            // Validación básica (ejemplo)
            if (usuario.isEmpty() || contrasena.isEmpty() || usuario.equals("Usuario") || contrasena.equals("Contraseña")) {
                JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                nombre = usuario;
                pass = contrasena;
                userField.setText("");
                passField.setText("");
                if (aceptado) {
                    ingresar(TheCallback);
                }
            }
        });

        // Acción del botón "Registrarse"
        registerButton.addActionListener(e -> {
            callback.ejecutar("registro");
        });

        // Agregar el panel principal a la ventana
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void ingresar(CallbackArryList callback) {
        if (aceptado) {
            var array = new ArrayList<String>();
            array.add(nombre);
            array.add(pass);
            callback.ejecutar(array);
        } else {
            TheCallback = callback;
            aceptado = true;
        }
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

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private static void setPlaceholder(JPasswordField field, String placeholder) {
        field.setEchoChar((char) 0); // Mostrar texto como placeholder
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('●'); // Volver a ocultar la contraseña
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setEchoChar((char) 0);
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }
}
