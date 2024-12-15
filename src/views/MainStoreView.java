package views;

import callbacks.callbackString;
import java.awt.*;
import javax.swing.*;

public class MainStoreView {

    public MainStoreView(callbackString callback) {
        JFrame frame = new JFrame("Panadería Esquina del Tío");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        Image icon = Toolkit.getDefaultToolkit().getImage("src/image/logo.png");
        frame.setIconImage(icon);

        JMenuBar menuBar = new JMenuBar();
        JMenu opcionesMenu = new JMenu("Opciones");

        JMenuItem carritoMenuItem = new JMenuItem("Carrito de Compras");
        JMenuItem cerrarSesionMenuItem = new JMenuItem("Cerrar Sesión");

        opcionesMenu.add(carritoMenuItem);
        opcionesMenu.add(cerrarSesionMenuItem);
        menuBar.add(opcionesMenu);
        frame.setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(createProductPanel("Pan Integral", 2.5, 10, "src/image/pan_integral.png"));
        mainPanel.add(createProductPanel("Pan Dulce", 1.75, 15, "src/image/pan_dulce.png"));
        mainPanel.add(createProductPanel("Croissant", 3.0, 8, "src/image/croissant.png"));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 220));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("<html><h2>Esquina del Tío</h2></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(139, 69, 19));

        JLabel logoLabel = new JLabel(new ImageIcon("src/image/logo.png"));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(welcomeLabel, BorderLayout.NORTH);
        topPanel.add(logoLabel, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        carritoMenuItem.addActionListener(e -> {
            CarritoDeCompras carrito = new CarritoDeCompras();
        });

        cerrarSesionMenuItem.addActionListener(e -> {
            if (callback == null) {
                System.exit(0);
            } else {
                callback.ejecutar("cerrar");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static JPanel createProductPanel(String name, double price, int quantity, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.setBackground(Color.WHITE);

        Producto producto = new Producto(name, price, quantity, imagePath);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage()
                .getScaledInstance(180, 120, Image.SCALE_SMOOTH)));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel infoLabel = new JLabel("<html><center><b>" + producto.getName() + "</b><br>"
                + "Precio: $" + producto.getPrice() + "<br>"
                + "Cantidad: " + producto.getQuantity() + "</center></html>", SwingConstants.CENTER);

        JButton buyButton = new JButton("Comprar");
        buyButton.setBackground(new Color(184, 134, 11));
        buyButton.setForeground(Color.WHITE);

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

        panel.add(imageLabel, BorderLayout.NORTH);
        panel.add(infoLabel, BorderLayout.CENTER);
        panel.add(buyButton, BorderLayout.SOUTH);

        return panel;
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainStoreView(null));
    }
}
