package Interface;

import logic.LogicAlcala;
import model.Product;
import persistence.Inventory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;

public class GUIEditProduct {

    private final JPanel panel;
    private final Inventory inventory;

    public GUIEditProduct(GUIstore guiStore, Product product, int index) throws IOException {
        LogicAlcala logicAlcala = guiStore.getLogicAlcala();
        this.inventory = GUIstore.getInventory();
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = null;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/Menu.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(backgroundImage.getImage(), 0, 0, panel.getWidth(), panel.getHeight(), this);
            }
        };

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        ImageIcon imageLogo = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/Logo.png")));
        Image image = imageLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledImageLogo = new ImageIcon(image);
        JLabel imgLogo = new JLabel(scaledImageLogo);
        topPanel.add(imgLogo, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new TitledBorder(null, "Edit Product", TitledBorder.CENTER, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(Color.white);
        lblName.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel JLabellblDescription = new JLabel("Description:");
        JLabellblDescription.setForeground(Color.white);
        JLabellblDescription.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setForeground(Color.white);
        lblPrice.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblStock = new JLabel("Stock:");
        lblStock.setForeground(Color.white);
        lblStock.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField txtName = new JTextField(product.getNameProduct(), 2);
        txtName.setColumns(20); // Ajusta el tamaño a 20 columnas
        txtName.setBorder(centerPanel.getBorder());
        JTextField txtDescription = new JTextField(product.getDescription(), 24);
        txtDescription.setColumns(20); // Ajusta el tamaño a 20 columnas
        JTextField txtPrice = new JTextField(String.valueOf(product.getPrice()), 24);
        txtPrice.setColumns(20); // Ajusta el tamaño a 20 columnas
        JSpinner spnStock = new JSpinner(new SpinnerNumberModel(product.getStock(), 0, Integer.MAX_VALUE, 1));

        // Agregar componentes al panel usando GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(JLabellblDescription, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(txtDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(lblPrice, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(txtPrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(lblStock, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        centerPanel.add(spnStock, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);

        JButton btnSave = new JButton("Save");
        btnSave.setForeground(Color.WHITE);
        btnSave.setBackground(Color.black);
        btnSave.setFont(new Font("Serif", Font.ITALIC, 14));
        btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(Color.black);
        btnCancel.setFont(new Font("Serif", Font.ITALIC, 14));
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        btnSave.addActionListener(e -> {
            // Actualizar el producto en la base de datos
            product.setNameProduct(txtName.getText());
            product.setDescription(txtDescription.getText());
            product.setPrice(Double.parseDouble(txtPrice.getText()));
            product.setStock((int) spnStock.getValue());

            if (txtName.getText().isEmpty() || txtDescription.getText().isEmpty() || txtPrice.getText().isEmpty()
                    || txtPrice.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
            } else if ((int) spnStock.getValue() < 1) {
                JOptionPane.showMessageDialog(null, "Ingrese un stok mayor a 0");
            } else {
                try {
                    Double.parseDouble(txtPrice.getText());
                    inventory.editProduct(txtName.getText(), txtDescription.getText(), txtPrice.getText(), String.valueOf(spnStock.getValue()), index);
                    JOptionPane.showMessageDialog(null, "Producto Editado");
                    guiStore.showCatalogPanel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor num�rico v�lido en el campo de precio");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        btnCancel.addActionListener(e -> {
            try {
                guiStore.showCatalogPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}