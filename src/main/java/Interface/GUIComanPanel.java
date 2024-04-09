package Interface;

import logic.LogicAlcala;
import model.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GUIComanPanel {
    private final JPanel panel;
    private final LogicAlcala logicAlcala;

    public GUIComanPanel(GUIstore guiStore) throws IOException {
        this.logicAlcala = guiStore.getLogicAlcala();
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/coman.png"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(backgroundImage.getImage(), 0, 0, panel.getWidth(), panel.getHeight(), this);
            }
        };

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        ImageIcon imageLogo = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logo.png"))));
        Image imageLog = imageLogo.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledImageLogo = new ImageIcon(imageLog);
        JLabel imgLog = new JLabel(scaledImageLogo);
        topPanel.add(imgLog, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbcCart = new GridBagConstraints();
        gbcCart.insets = new Insets(10, 10, 10, 10);

        JLabel imagePro = new JLabel("Imagen");
        imagePro.setForeground(Color.WHITE);
        JLabel nameLabelTitle = new JLabel("Nombre");
        nameLabelTitle.setForeground(Color.WHITE);
        JLabel priceLabelTitle = new JLabel("Precio");
        priceLabelTitle.setForeground(Color.WHITE);
        JLabel numberLabel = new JLabel("Cantidad");
        numberLabel.setForeground(Color.WHITE);

        gbcCart.gridy++;
        gbcCart.gridx++;
        centerPanel.add(imagePro, gbcCart);
        gbcCart.gridx++;
        centerPanel.add(nameLabelTitle, gbcCart);
        gbcCart.gridx++;
        centerPanel.add(priceLabelTitle, gbcCart);
        gbcCart.gridx++;
        centerPanel.add(numberLabel, gbcCart);
        gbcCart.gridx++;

        ImageIcon eraseIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/eraseCar.png"))));
        Image eraseImage = eraseIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledEraseIcon = new ImageIcon(eraseImage);

        for (Product product : logicAlcala.getCart().getProducts()) {
            ImageIcon imageProduct = null;
            try {
                InputStream inputStream = getClass().getResourceAsStream("/Icons/" + product.getId() + ".png");
                if (inputStream == null) {
                    inputStream = getClass().getResourceAsStream("/Icons/default.png");
                }
                imageProduct = new ImageIcon(ImageIO.read(inputStream));
            } catch (IOException r) {
                r.printStackTrace();
            }

            Image image = imageProduct.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledImageProduct = new ImageIcon(image);
            JLabel imgProduct = new JLabel(scaledImageProduct);

            JLabel nameLabel = new JLabel(product.getNameProduct());
            nameLabel.setForeground(Color.WHITE);
            JLabel priceLabel = new JLabel("$" + product.getPrice());
            priceLabel.setForeground(Color.WHITE);
            JLabel purchased = new JLabel(String.valueOf(logicAlcala.getCart().getPurchased(product)));
            purchased.setForeground(Color.WHITE);
            JButton removeButton = new JButton("Eliminar de la Comanda", scaledEraseIcon);
            removeButton.setFont(new Font("Serif", Font.ITALIC, 12));
            removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            gbcCart.gridy++;
            gbcCart.gridx = 0;
            centerPanel.add(imgProduct, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(nameLabel, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(priceLabel, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(purchased, gbcCart);
            gbcCart.gridx++;
            centerPanel.add(removeButton, gbcCart);

            removeButton.addActionListener(e -> {
                logicAlcala.eraseProductCart(product);
                JOptionPane.showMessageDialog(guiStore.getFrame(), "Producto eliminado de la Comanda.");
                try {
                    guiStore.showCartPanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        JLabel totalLabel = new JLabel("Total: $" + logicAlcala.getCart().calcTotal());
        totalLabel.setForeground(Color.WHITE);
        gbcCart.gridy++;
        gbcCart.gridx = 3;
        centerPanel.add(totalLabel, gbcCart);

        ImageIcon clearIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/vaciarCar1.png"))));
        Image clearImage = clearIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledClaerIcon = new ImageIcon(clearImage);
        JButton clearButton = new JButton(" Vaciar  Comanda \n  ", scaledClaerIcon);
        clearButton.setFont(new Font("Serif", Font.ITALIC, 12));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.black);
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridy++;
        gbcCart.gridx = -1;
        centerPanel.add(clearButton, gbcCart);

        ImageIcon checkoutIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/checkout.png"))));
        Image checkoutImage = checkoutIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledCheckoutIcon = new ImageIcon(checkoutImage);
        JButton transfButton = new JButton("Transferencia", scaledCheckoutIcon);
        transfButton.setForeground(Color.WHITE);
        transfButton.setBackground(Color.black);
        transfButton.setFont(new Font("Serif", Font.ITALIC, 14));
        transfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridx = 2;
        centerPanel.add(transfButton, gbcCart);

        JButton datafButton = new JButton("DATAFONO", scaledCheckoutIcon);
        datafButton.setForeground(Color.WHITE);
        datafButton.setBackground(Color.black);
        datafButton.setFont(new Font("Serif", Font.ITALIC, 14));
        datafButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridx = 3;
        centerPanel.add(datafButton, gbcCart);

        JButton normalyButton = new JButton("Realizar Comanda", scaledCheckoutIcon);
        normalyButton.setForeground(Color.WHITE);
        normalyButton.setBackground(Color.black);
        normalyButton.setFont(new Font("Serif", Font.ITALIC, 14));
        normalyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbcCart.gridx = 4;
        centerPanel.add(normalyButton, gbcCart);

        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setOpaque(false);

        ImageIcon backIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/back.png"))));
        Image backImage = backIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledBackIcon = new ImageIcon(backImage);
        JButton backButton = new JButton("Atrás", scaledBackIcon);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.black);
        backButton.setFont(new Font("Serif", Font.ITALIC, 11));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttomPanel.add(backButton);

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = null;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/comand.png"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(backgroundImage.getImage(), 0, 0, backgroundImage.getIconWidth() + 500, backgroundImage.getIconHeight(), this);
            }
        };
        JScrollPane scrollPanel = new JScrollPane(backgroundPanel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = scrollPanel.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(scrollPanel, BorderLayout.CENTER);
        panel.add(buttomPanel, BorderLayout.PAGE_END);

        clearButton.addActionListener(e -> {
            logicAlcala.clearCart();
            try {
                guiStore.showMenuPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        transfButton.addActionListener(e -> {
            try {
                String[] options = {"NEQUI", "DAVIPLATA", "BONCOLOMBIA"};
                String selectedOption = (String) JOptionPane.showInputDialog(null, "Selecciona una opción", "Tipo de transf", JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                logicAlcala.setTipeTransfe(selectedOption);
                logicAlcala.makePurchase("transferencia");
                guiStore.getBox().writeToFile();
                JOptionPane.showMessageDialog(guiStore.getFrame(), logicAlcala.getFacture());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                guiStore.showMenuPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        datafButton.addActionListener(e -> {
            try {
                int option = JOptionPane.showOptionDialog(null, "¿Con Propina?", "Propina",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new Object[]{"Sí", "No"}, "No");
                if (option == 0) {// Se seleccionó la opción "Sí"
                    logicAlcala.setPropina(Integer.parseInt(JOptionPane.showInputDialog("Valor Propina")));
                }
                logicAlcala.makePurchase("datafono");
                guiStore.getBox().writeToFile();
                JOptionPane.showMessageDialog(guiStore.getFrame(), logicAlcala.getFacture());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                guiStore.showMenuPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        normalyButton.addActionListener(e -> {
            try {
                logicAlcala.makePurchase("noramaly");
                guiStore.getBox().writeToFile();
                JOptionPane.showMessageDialog(guiStore.getFrame(), logicAlcala.getFacture());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                guiStore.showMenuPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButton.addActionListener(e -> {
            try {
                guiStore.showMenuPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}