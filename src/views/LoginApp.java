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
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        frame.setIconImage(icon);

        this.Frame = frame;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 220));

        JLabel titleLabel = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setBackground(new Color(245, 245, 220));

        JTextField userField = new JTextField();
        setPlaceholder(userField, "Usuario");

        JPasswordField passField = new JPasswordField();
        setPlaceholder(passField, "Contraseña");

        formPanel.add(userField);
        formPanel.add(passField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(245, 245, 220));

        JButton loginButton = new JButton("Iniciar Sesión");
        styleButton(loginButton, new Color(184, 134, 11));
        buttonPanel.add(loginButton, BorderLayout.CENTER);

        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerPanel.setBackground(new Color(245, 245, 220));
        JLabel registerLabel = new JLabel("¿No se ha registrado?");
        registerLabel.setForeground(new Color(139, 69, 19));
        JButton registerButton = new JButton("Registrarse");
        styleButton(registerButton, new Color(70, 130, 180));

        registerPanel.add(registerLabel);
        registerPanel.add(registerButton);
        buttonPanel.add(registerPanel, BorderLayout.SOUTH);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            String usuario = userField.getText();
            String contrasena = new String(passField.getPassword());

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

        registerButton.addActionListener(e -> {
            callback.ejecutar("registro");
        });

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

    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 30));
    }

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
        field.setEchoChar((char) 0);
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('●');
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
