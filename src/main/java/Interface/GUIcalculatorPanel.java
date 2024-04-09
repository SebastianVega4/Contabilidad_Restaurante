package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GUIcalculatorPanel {
    private final JPanel panel;

    public GUIcalculatorPanel(GUIstore guiStore) throws IOException {
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/calcu.png"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(backgroundImage.getImage(), 0, 0, panel.getWidth(), panel.getHeight(), this);
            }
        };
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        ImageIcon imageLogo = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/logo.png"))));
        Image image = imageLogo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledImageLogo = new ImageIcon(image);
        JLabel imgLogo = new JLabel(scaledImageLogo);
        topPanel.add(imgLogo, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new TitledBorder(null, "Edit Product", TitledBorder.CENTER, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre los componentes

        JLabel label2 = new JLabel("2.000:");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField num2 = new JTextField();
        num2.setPreferredSize(new Dimension(100, 25)); // Aumentar el tamaño del JSpinner

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(label2, gbc);

        gbc.gridx = 1;
        centerPanel.add(num2, gbc);

        JLabel label5 = new JLabel("5.000:");
        label5.setForeground(Color.white);
        label5.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField num5 = new JTextField();
        num5.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(label5, gbc);

        gbc.gridx = 1;
        centerPanel.add(num5, gbc);

        JLabel label10 = new JLabel("10.000:");
        label10.setForeground(Color.white);
        label10.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField num10 = new JTextField();
        num10.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(label10, gbc);

        gbc.gridx = 1;
        centerPanel.add(num10, gbc);

        JLabel label20 = new JLabel("20.000:");
        label20.setForeground(Color.white);
        label20.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField num20 = new JTextField();
        num20.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(label20, gbc);

        gbc.gridx = 1;
        centerPanel.add(num20, gbc);

        JLabel label50 = new JLabel("50.000:");
        label50.setForeground(Color.white);
        label50.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField num50 = new JTextField();
        num50.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 4;
        centerPanel.add(label50, gbc);

        gbc.gridx = 1;
        centerPanel.add(num50, gbc);

        JLabel label100 = new JLabel("100.000:");
        label100.setForeground(Color.white);
        label100.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField num100 = new JTextField();
        num100.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 5;
        centerPanel.add(label100, gbc);

        gbc.gridx = 1;
        centerPanel.add(num100, gbc);

        JButton calculateButton = new JButton("Calcular Total");
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBackground(Color.black);
        calculateButton.setFont(new Font("Serif", Font.ITALIC, 14));
        calculateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 6;
        centerPanel.add(calculateButton, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setOpaque(false);

        ImageIcon backIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icons/back.png"))));
        Image backImage = backIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledBackIcon = new ImageIcon(backImage);
        JButton backButton = new JButton("Atras", scaledBackIcon);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.black);
        backButton.setFont(new Font("Serif", Font.ITALIC, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(backButton);

        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        calculateButton.addActionListener(e -> {
            int num2Value;
            try {
                num2Value = Integer.parseInt(num2.getText());
            } catch (NumberFormatException r) {
                num2Value = 0;
            }
            int num5Value;
            try {
                num5Value = Integer.parseInt(num5.getText());
            } catch (NumberFormatException r) {
                num5Value = 0;
            }
            int num10Value;
            try {
                num10Value = Integer.parseInt(num10.getText());
            } catch (NumberFormatException r) {
                num10Value = 0;
            }
            int num20Value;
            try {
                num20Value = Integer.parseInt(num20.getText());
            } catch (NumberFormatException r) {
                num20Value = 0;
            }
            int num50Value;
            try {
                num50Value = Integer.parseInt(num50.getText());
            } catch (NumberFormatException r) {
                num50Value = 0;
            }
            int num100Value;
            try {
                num100Value = Integer.parseInt(num100.getText());
            } catch (NumberFormatException r) {
                num100Value = 0;
            }

            int billetes = (2_000 * num2Value) +
                    (5_000 * num5Value) +
                    (10_000 * num10Value) +
                    (20_000 * num20Value) +
                    (50_000 * num50Value) +
                    (100_000 * num100Value);
            JOptionPane.showMessageDialog(null, "Total:  $" + billetes, "Total", JOptionPane.INFORMATION_MESSAGE);
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
