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
        mainPanel.add(createProductPanel("Pan Integral", "$2.5", "10", "pan1.jpg"));
        mainPanel.add(createProductPanel("Pan Dulce", "$1.75", "15", "pan2.jpg"));
        mainPanel.add(createProductPanel("Croissant", "$3.0", "8", "pan3.jpg"));

        // Agregar el panel principal al centro
        frame.add(mainPanel, BorderLayout.CENTER);

        // Hacer visible la interfaz
        frame.setVisible(true);
    }

    private static JPanel createProductPanel(String name, String price, String quantity, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panel.setBackground(Color.WHITE);

        // Imagen del producto (imagen de ejemplo)
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(new ImageIcon("src/" + imagePath).getImage()
                .getScaledInstance(180, 120, Image.SCALE_SMOOTH)));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Etiqueta de información
        JLabel infoLabel = new JLabel("<html><center><b>" + name + "</b><br>"
                + "Precio: " + price + "<br>"
                + "Cantidad: " + quantity + "</center></html>", SwingConstants.CENTER);

        // Botón de compra
        JButton buyButton = new JButton("Comprar");
        buyButton.setBackground(new Color(184, 134, 11)); // Color marrón
        buyButton.setForeground(Color.WHITE);

        // Añadir componentes al panel
        panel.add(imageLabel, BorderLayout.NORTH);
        panel.add(infoLabel, BorderLayout.CENTER);
        panel.add(buyButton, BorderLayout.SOUTH);

        return panel;
    }
}
