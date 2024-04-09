package Interface;

import logic.LogicAlcala;
import model.Product;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GUICartaPanel {
    private final JPanel panel;
    private final LogicAlcala logicAlcala;
    private final List<Product> filteredProducts;

    public GUICartaPanel(GUIstore guiStore) throws IOException {
        this.logicAlcala = guiStore.getLogicAlcala();
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/carta.png"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(backgroundImage.getImage(), 0, 0, panel.getWidth(), panel.getHeight(), this);
            }
        };

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        topPanel.setOpaque(false);

        ImageIcon imageLogo = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logo.png"))));
        Image imageL = imageLogo.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledImageLogo = new ImageIcon(imageL);
        JLabel imgLogo = new JLabel(scaledImageLogo);
        gbc.gridx = 0;
        topPanel.add(imgLogo, gbc);

        JTextField searchField = new JTextField();
        searchField.setColumns(35);
        JButton searchButton = new JButton("Buscar");
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newTimer();
            }

            private void newTimer() {
                Timer timer = new Timer(1000, (ActionEvent e) -> {
                    searchButton.doClick();
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        gbc.gridx++;
        topPanel.add(searchField, gbc);
        gbc.gridx++;
        topPanel.add(searchButton, gbc);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbcProduct = new GridBagConstraints();
        gbcProduct.insets = new Insets(10, 10, 10, 10);

        JLabel imageProTitle = new JLabel("Imagen");
        imageProTitle.setForeground(Color.WHITE);
        JLabel nameLabelTitle = new JLabel("Nombre");
        nameLabelTitle.setForeground(Color.WHITE);
        JLabel descriptionLabelTitle = new JLabel("Descripción");
        descriptionLabelTitle.setForeground(Color.WHITE);
        JLabel priceLabelTitle = new JLabel("Precio");
        priceLabelTitle.setForeground(Color.WHITE);
        JLabel numberLabelTitle = new JLabel("Cantidad");
        numberLabelTitle.setForeground(Color.WHITE);
        JLabel editLabelTitle = new JLabel("Editar");
        editLabelTitle.setForeground(Color.WHITE);

        gbcProduct.gridy++;
        gbcProduct.gridx++;
        centerPanel.add(imageProTitle, gbcProduct);
        gbcProduct.gridx++;
        centerPanel.add(nameLabelTitle, gbcProduct);
        gbcProduct.gridx++;
        centerPanel.add(descriptionLabelTitle, gbcProduct);
        gbcProduct.gridx++;
        centerPanel.add(priceLabelTitle, gbcProduct);
        gbcProduct.gridx++;
        centerPanel.add(numberLabelTitle, gbcProduct);

        ImageIcon addIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/add.png"))));
        Image addImage = addIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledAddIcon = new ImageIcon(addImage);

        ImageIcon editIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/edit.png"))));
        Image editImage = editIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scalededitIcon = new ImageIcon(editImage);

        filteredProducts = new ArrayList<>(GUIstore.getInventory().getProducts());

        for (Product product : filteredProducts) {
            ImageIcon imageProduct = null;
            try {
                InputStream inputStream = getClass().getResourceAsStream("/Icons/" + product.getId() + ".png");
                if (inputStream == null) {
                    inputStream = getClass().getResourceAsStream("/Icons/default.png");
                }
                imageProduct = new ImageIcon(ImageIO.read(inputStream));
            } catch (IOException e) {
                // Manejar la excepción adecuadamente, por ejemplo, mostrando un mensaje de error
                e.printStackTrace();
            }

            Image image = imageProduct.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledImageProduct = new ImageIcon(image);
            JLabel imgProduct = new JLabel(scaledImageProduct);

            JLabel nameLabel = new JLabel(product.getNameProduct());
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("Serif", Font.ITALIC, 12));
            JLabel descriptionLabel = new JLabel(product.getDescription());
            descriptionLabel.setMaximumSize(new Dimension(1, 1));
            descriptionLabel.setForeground(Color.WHITE);
            descriptionLabel.setFont(new Font("Serif", Font.ITALIC, 12));
            JLabel priceLabel = new JLabel("$" + product.getPrice());
            priceLabel.setForeground(Color.WHITE);
            priceLabel.setFont(new Font("Serif", Font.ITALIC, 12));
            JSpinner buys = new JSpinner();
            buys.setValue(1);

            JButton addButtonModi = new JButton("Modificar", scalededitIcon);
            addButtonModi.setFont(new Font("Serif", Font.ITALIC, 14));
            addButtonModi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JButton addButton = new JButton("Agregar a la comanda", scaledAddIcon);
            addButton.setFont(new Font("Serif", Font.ITALIC, 14));
            addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            gbcProduct.gridy++;
            gbcProduct.gridx = 0;
            centerPanel.add(imgProduct, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(nameLabel, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(descriptionLabel, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(priceLabel, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(buys, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(addButtonModi, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(addButton, gbcProduct);

            buys.addChangeListener(e -> logicAlcala.addNumberPurchesed(product, (Integer) buys.getValue()));

            addButtonModi.addActionListener(e -> {
                try {
                    guiStore.showEditProdut(product, product.getId());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            addButton.addActionListener(e -> {
                if ((Integer) buys.getValue() == 0)
                    JOptionPane.showMessageDialog(guiStore.getFrame(), "Ingrese alguna cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                else if ((Integer) buys.getValue() < 0)
                    JOptionPane.showMessageDialog(guiStore.getFrame(), "No se pueden añadir al carrito cantidades negativas.", "Error", JOptionPane.ERROR_MESSAGE);
                else if ((Integer) buys.getValue() > product.getStock())
                    JOptionPane.showMessageDialog(guiStore.getFrame(), "No hay suficiente Stock del Articulo: '" + product.getNameProduct(), "Error", JOptionPane.ERROR_MESSAGE);
                else if ((Integer) buys.getValue() > 0) {
                    logicAlcala.addNumberPurchesed(product, (Integer) buys.getValue());
                    JOptionPane.showMessageDialog(guiStore.getFrame(), (logicAlcala.addPurchased(product)));
                    searchField.setText("");
                    searchField.requestFocusInWindow();
                }
            });

            ImageIcon finalImageProduct = imageProduct;
            imgProduct.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    Image image = finalImageProduct.getImage().getScaledInstance(130, 190, Image.SCALE_SMOOTH);
                    ImageIcon scaledImageProduct = new ImageIcon(image);
                    imgProduct.setIcon(scaledImageProduct);
                }

                public void mouseExited(MouseEvent evt) {
                    Image image = finalImageProduct.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    ImageIcon scaledImageProduct = new ImageIcon(image);
                    imgProduct.setIcon(scaledImageProduct);
                }
            });
        }

        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setOpaque(false);

        ImageIcon carIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/carrito.png"))));
        Image carImage = carIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledcarIcon = new ImageIcon(carImage);
        JButton carButton = new JButton("Comanda", scaledcarIcon);
        carButton.setForeground(Color.WHITE);
        carButton.setBackground(Color.GRAY);
        carButton.setFont(new Font("Serif", Font.ITALIC, 14));
        carButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttomPanel.add(carButton);

        ImageIcon backIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/back.png"))));
        Image backImage = backIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledBackIcon = new ImageIcon(backImage);
        JButton backButton = new JButton("Atrás", scaledBackIcon);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.black);
        backButton.setFont(new Font("Serif", Font.ITALIC, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttomPanel.add(backButton);

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/caja.png"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(backgroundImage.getImage(), 0, 0, 10000, 10000, this);
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

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().toLowerCase();
            filteredProducts.clear();

            for (Product product : GUIstore.getInventory().getProducts()) {
                if (product.getNameProduct().toLowerCase().contains(searchText)) {
                    filteredProducts.add(product);
                }
            }

            centerPanel.removeAll();

            gbcProduct.gridy = 0;
            gbcProduct.gridx = 0;
            centerPanel.add(imageProTitle, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(nameLabelTitle, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(descriptionLabelTitle, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(priceLabelTitle, gbcProduct);
            gbcProduct.gridx++;
            centerPanel.add(numberLabelTitle, gbcProduct);

            for (Product product : filteredProducts) {
                ImageIcon imageProduct = null;
                try {
                    InputStream inputStream = getClass().getResourceAsStream("/Icons/" + product.getId() + ".png");
                    if (inputStream == null) {
                        inputStream = getClass().getResourceAsStream("/Icons/default.png");
                    }
                    imageProduct = new ImageIcon(ImageIO.read(inputStream));
                } catch (IOException r) {
                    // Manejar la excepción adecuadamente, por ejemplo, mostrando un mensaje de error
                    r.printStackTrace();
                }

                Image image = imageProduct.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledImageProduct = new ImageIcon(image);
                JLabel imgProduct = new JLabel(scaledImageProduct);

                JLabel nameLabel = new JLabel(product.getNameProduct());
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setFont(new Font("Serif", Font.ITALIC, 12));
                JLabel descriptionLabel = new JLabel(product.getDescription());
                descriptionLabel.setMaximumSize(new Dimension(1, 1));
                descriptionLabel.setForeground(Color.WHITE);
                descriptionLabel.setFont(new Font("Serif", Font.ITALIC, 12));
                JLabel priceLabel = new JLabel("$" + product.getPrice());
                priceLabel.setForeground(Color.WHITE);
                priceLabel.setFont(new Font("Serif", Font.ITALIC, 12));
                JSpinner buys = new JSpinner();
                buys.setValue(1);

                JButton addButtonModi = new JButton("Modificar", scalededitIcon);
                addButtonModi.setFont(new Font("Serif", Font.ITALIC, 14));
                addButtonModi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                JButton addButton = new JButton("Agregar a la comanda", scaledAddIcon);
                addButton.setFont(new Font("Serif", Font.ITALIC, 14));
                addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                gbcProduct.gridy++;
                gbcProduct.gridx = 0;
                centerPanel.add(imgProduct, gbcProduct);
                gbcProduct.gridx++;
                centerPanel.add(nameLabel, gbcProduct);
                gbcProduct.gridx++;
                centerPanel.add(descriptionLabel, gbcProduct);
                gbcProduct.gridx++;
                centerPanel.add(priceLabel, gbcProduct);
                gbcProduct.gridx++;
                centerPanel.add(buys, gbcProduct);
                gbcProduct.gridx++;
                centerPanel.add(addButtonModi, gbcProduct);
                gbcProduct.gridx++;
                centerPanel.add(addButton, gbcProduct);

                buys.addChangeListener(r -> logicAlcala.addNumberPurchesed(product, (Integer) buys.getValue()));

                addButtonModi.addActionListener(r -> {
                    try {
                        guiStore.showEditProdut(product, product.getId());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                addButton.addActionListener(r -> {
                    if ((Integer) buys.getValue() == 0)
                        JOptionPane.showMessageDialog(guiStore.getFrame(), "Ingrese alguna cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                    else if ((Integer) buys.getValue() < 0)
                        JOptionPane.showMessageDialog(guiStore.getFrame(), "No se pueden añadir al carrito cantidades negativas.", "Error", JOptionPane.ERROR_MESSAGE);
                    else if ((Integer) buys.getValue() > product.getStock())
                        JOptionPane.showMessageDialog(guiStore.getFrame(), "No hay suficiente Stock del Articulo: '" + product.getNameProduct(), "Error", JOptionPane.ERROR_MESSAGE);
                    else if ((Integer) buys.getValue() > 0) {
                        logicAlcala.addNumberPurchesed(product, (Integer) buys.getValue());
                        JOptionPane.showMessageDialog(guiStore.getFrame(), (logicAlcala.addPurchased(product)));
                        searchField.setText("");
                        searchField.requestFocusInWindow();
                    }
                });

                ImageIcon finalImageProduct = imageProduct;
                imgProduct.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent evt) {
                        Image image = finalImageProduct.getImage().getScaledInstance(130, 190, Image.SCALE_SMOOTH);
                        ImageIcon scaledImageProduct = new ImageIcon(image);
                        imgProduct.setIcon(scaledImageProduct);
                    }

                    public void mouseExited(MouseEvent evt) {
                        Image image = finalImageProduct.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                        ImageIcon scaledImageProduct = new ImageIcon(image);
                        imgProduct.setIcon(scaledImageProduct);
                    }
                });
            }

            centerPanel.revalidate();
            centerPanel.repaint();
        });

        searchField.addActionListener(r -> searchButton.doClick());
        backButton.addActionListener(e -> {
            try {
                guiStore.showMenuPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        carButton.addActionListener(e -> {
            try {
                guiStore.showCartPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}