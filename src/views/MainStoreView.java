package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;

public class MainStoreView {

    public MainStoreView(callbackString callback) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Panadería Esquina del Tío");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setLayout(new BorderLayout());

        // Establecer el icono de la aplicación
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        frame.setIconImage(icon);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu opcionesMenu = new JMenu("Opciones");

        JMenuItem carritoMenuItem = new JMenuItem("Carrito de Compras");
        JMenuItem cerrarSesionMenuItem = new JMenuItem("Cerrar Sesión");

        opcionesMenu.add(carritoMenuItem);
        opcionesMenu.add(cerrarSesionMenuItem);
        menuBar.add(opcionesMenu);
        frame.setJMenuBar(menuBar);

        // Crear un panel principal para los productos
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 15, 15)); // 3 columnas, espacio de 15px
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes exteriores

        // Agregar productos
        mainPanel.add(createProductPanel("Pan Integral", 2.5, 10, "src/image/pan_integral.png"));
        mainPanel.add(createProductPanel("Pan Dulce", 1.75, 15, "src/image/pan_dulce.png"));
        mainPanel.add(createProductPanel("Croissant", 3.0, 8, "src/image/croissant.png"));

        // Crear un panel superior para el mensaje de bienvenida y el logo
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 220)); // Fondo beige
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        JLabel welcomeLabel = new JLabel("<html><h2>Esquina del Tío</h2></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(139, 69, 19)); // Texto marrón oscuro

        JLabel logoLabel = new JLabel(new ImageIcon("src/image/logo.png"));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(welcomeLabel, BorderLayout.NORTH);
        topPanel.add(logoLabel, BorderLayout.CENTER);

        // Agregar el panel superior al norte y el panel principal al centro
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Hacer visible la interfaz
        frame.setVisible(true);
    }

    private static JPanel createProductPanel(String name, double price, int quantity, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.setBackground(Color.WHITE);

        // Crear un objeto Producto
        Producto producto = new Producto(name, price, quantity, imagePath);

        // Imagen del producto
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage()
                .getScaledInstance(180, 120, Image.SCALE_SMOOTH)));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Etiqueta de información
        JLabel infoLabel = new JLabel("<html><center><b>" + producto.getName() + "</b><br>"
                + "Precio: $" + producto.getPrice() + "<br>"
                + "Cantidad: " + producto.getQuantity() + "</center></html>", SwingConstants.CENTER);

        // Botón de compra
        JButton buyButton = new JButton("Comprar");
        buyButton.setBackground(new Color(184, 134, 11)); // Color marrón
        buyButton.setForeground(Color.WHITE);

        // Acción del botón de compra
        buyButton.addActionListener(e -> {
            if (producto.getQuantity() > 0) {
                producto.decreaseQuantity();
                JOptionPane.showMessageDialog(panel, "Producto añadido", "Información", JOptionPane.INFORMATION_MESSAGE);
                infoLabel.setText("<html><center><b>" + producto.getName() + "</b><br>"
                        + "Precio: $" + producto.getPrice() + "<br>"
                        + "Cantidad: " + producto.getQuantity() + "</center></html>");
            } else {
                JOptionPane.showMessageDialog(panel, "Producto agotado", "Información", JOptionPane.WARNING_MESSAGE);
                buyButton.setEnabled(false);
            }
        });

        // Añadir componentes al panel
        panel.add(imageLabel, BorderLayout.NORTH);
        panel.add(infoLabel, BorderLayout.CENTER);
        panel.add(buyButton, BorderLayout.SOUTH);

        return panel;
    }

    // Clase Producto para encapsular los detalles del producto
    static class Producto {
        private String name;
        private double price;
        private int quantity;
        private String imagePath;

        public Producto(String name, double price, int quantity, String imagePath) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void decreaseQuantity() {
            if (quantity > 0) {
                quantity--;
            }
        }
    }

    // Método principal para probar la clase de forma aislada
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainStoreView(null));
    }
}
