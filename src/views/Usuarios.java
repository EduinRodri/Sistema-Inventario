package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Usuarios extends JFrame {
    private JTable usuariosTable;
    private DefaultTableModel tableModel;

    public Usuarios(callbackString callback) {
        // Configuración del JFrame
        setTitle("Gestión de Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setResizable(false);

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        setIconImage(icon);

        // Etiqueta superior
        JLabel titleLabel = new JLabel("Usuarios Registrados", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Modelo de tabla
        String[] columnNames = {"ID", "Nombre de Usuario", "Correo Electrónico", "Rol"};
        Object[][] data = {
                {"1", "admin", "admin@ejemplo.com", "Administrador"},
                {"2", "jose123", "jose@ejemplo.com", "Usuario"},
                {"3", "maria456", "maria@ejemplo.com", "Usuario"},
                {"4", "lucas789", "lucas@ejemplo.com", "Editor"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        usuariosTable = new JTable(tableModel);

        // Configuración de la tabla
        usuariosTable.setFont(new Font("Arial", Font.PLAIN, 14));
        usuariosTable.setRowHeight(25);
        usuariosTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        usuariosTable.setEnabled(false); // Tabla no editable

        // Colocar la tabla en un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(usuariosTable);
        tableScrollPane.setPreferredSize(new Dimension(660, 250));
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Botón agregar usuario
        JButton addButton = new JButton("Agregar Usuario");
        styleButton(addButton, new Color(34, 139, 34)); // Botón verde

        // Botón cerrar
        JButton closeButton = new JButton("Cerrar");
        styleButton(closeButton, new Color(184, 70, 11)); // Botón rojo suave

        // Panel inferior para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        buttonPanel.add(addButton);
        buttonPanel.add(closeButton);

        // Layout principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes externos
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        // Acciones de los botones
        addButton.addActionListener(e -> openAddUserDialog());
        closeButton.addActionListener(e -> dispose());
    }

    // Método para abrir el diálogo de agregar usuario
    private void openAddUserDialog() {
        JDialog addUserDialog = new JDialog(this, "Agregar Usuario", true);
        addUserDialog.setSize(400, 300);
        addUserDialog.setLocationRelativeTo(this);
        addUserDialog.setLayout(new GridBagLayout());
        addUserDialog.getContentPane().setBackground(new Color(245, 245, 220)); // Fondo beige

        // Componentes del formulario
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(15);

        JLabel usernameLabel = new JLabel("Nombre de Usuario:");
        JTextField usernameField = new JTextField(15);

        JLabel emailLabel = new JLabel("Correo Electrónico:");
        JTextField emailField = new JTextField(15);

        JLabel roleLabel = new JLabel("Rol:");
        String[] roles = {"Usuario", "Administrador", "Editor"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);

        JButton saveButton = new JButton("Guardar");
        styleButton(saveButton, new Color(34, 139, 34)); // Botón verde

        JButton cancelButton = new JButton("Cancelar");
        styleButton(cancelButton, new Color(184, 70, 11)); // Botón rojo suave

        // Añadir componentes al diálogo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        addUserDialog.add(idLabel, gbc);

        gbc.gridx = 1;
        addUserDialog.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addUserDialog.add(usernameLabel, gbc);

        gbc.gridx = 1;
        addUserDialog.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addUserDialog.add(emailLabel, gbc);

        gbc.gridx = 1;
        addUserDialog.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addUserDialog.add(roleLabel, gbc);

        gbc.gridx = 1;
        addUserDialog.add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel dialogButtonPanel = new JPanel();
        dialogButtonPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        dialogButtonPanel.add(saveButton);
        dialogButtonPanel.add(cancelButton);
        addUserDialog.add(dialogButtonPanel, gbc);

        // Acciones de los botones en el diálogo
        saveButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String role = (String) roleComboBox.getSelectedItem();

            if (!id.isEmpty() && !username.isEmpty() && !email.isEmpty()) {
                tableModel.addRow(new Object[]{id, username, email, role});
                addUserDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(addUserDialog, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> addUserDialog.dispose());

        addUserDialog.setVisible(true);
    }

    // Método para estilizar botones
    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 30));
    }

    // Método para probar la clase de forma aislada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Usuarios(null));
    }
}
