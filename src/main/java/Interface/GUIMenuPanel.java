package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIMenuPanel {
    private final JPanel panel;

    public GUIMenuPanel(GUIstore guiStore) throws IOException {
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = null;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/menu.png")));
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

        ImageIcon catalogIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/catalogo.png")));
        Image catalogImage = catalogIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledCatalogIcon = new ImageIcon(catalogImage);
        JButton catalogButton = new JButton("Catálogo", scaledCatalogIcon);
        catalogButton.setPreferredSize(new Dimension(200, 200));
        catalogButton.setFont(new Font("Serif", Font.ITALIC, 14));
        catalogButton.setBackground(new Color(63, 81, 181));
        catalogButton.setForeground(Color.WHITE);
        catalogButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        catalogButton.setFocusPainted(false);
        catalogButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        centerPanel.add(catalogButton);

        ImageIcon cartIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/carrito.png")));
        Image cartImage = cartIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledCartIcon = new ImageIcon(cartImage);
        JButton cartButton = new JButton("Comanda", scaledCartIcon);
        cartButton.setPreferredSize(new Dimension(200, 200));
        cartButton.setFont(new Font("Serif", Font.ITALIC, 14));
        cartButton.setBackground(new Color(63, 81, 181));
        cartButton.setForeground(Color.WHITE);
        cartButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        cartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        centerPanel.add(cartButton);

        ImageIcon boxIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/box.png")));
        Image boxImage = boxIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledboxIcon = new ImageIcon(boxImage);
        JButton boxBotton = new JButton("CAJA", scaledboxIcon);
        boxBotton.setPreferredSize(new Dimension(200, 200));
        boxBotton.setFont(new Font("Serif", Font.ITALIC, 14));
        boxBotton.setBackground(new Color(63, 81, 181));
        boxBotton.setForeground(Color.WHITE);
        boxBotton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        boxBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        centerPanel.add(boxBotton);

        ImageIcon calIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/calcula.png")));
        Image calImage = calIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledcalIcon = new ImageIcon(calImage);
        JButton calculatorBotton = new JButton("CALCULADORA", scaledcalIcon);
        calculatorBotton.setPreferredSize(new Dimension(200, 200));
        calculatorBotton.setFont(new Font("Serif", Font.ITALIC, 14));
        calculatorBotton.setBackground(new Color(63, 81, 181));
        calculatorBotton.setForeground(Color.WHITE);
        calculatorBotton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        calculatorBotton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        centerPanel.add(calculatorBotton);

        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setOpaque(false);

        ImageIcon signoutIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/signout11.png")));
        Image signoutImage = signoutIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledSignoutIcon = new ImageIcon(signoutImage);
        JButton signOffButton = new JButton("Cerrar Sesión", scaledSignoutIcon);

        signOffButton.setForeground(Color.WHITE);
        signOffButton.setBackground(Color.black);
        signOffButton.setFont(new Font("Serif", Font.ITALIC, 9));
        signOffButton.setPreferredSize(new Dimension(150, 30));
        signOffButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttomPanel.add(signOffButton);

        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttomPanel, BorderLayout.PAGE_END);

        catalogButton.addActionListener(e -> {
            try {
                guiStore.showCatalogPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        cartButton.addActionListener(e -> {
            try {
                guiStore.showCartPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        boxBotton.addActionListener(e -> {
            try {
                guiStore.showBoxPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        calculatorBotton.addActionListener(e -> {
            try {
                guiStore.showCalculatorPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        signOffButton.addActionListener(e -> {
            try {
                guiStore.showLoginPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}