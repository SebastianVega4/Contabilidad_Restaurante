package Interface;

import logic.LogicAlcala;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GUIboxPanel {
    private final JPanel panel;

    public GUIboxPanel(GUIstore guiStore) throws IOException {
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = null;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/caja.png")));
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
        centerPanel.setBorder(new TitledBorder(null, "CAJA", TitledBorder.CENTER, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton initButton = new JButton("Iniciar CAJA");
        initButton.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField valBox = new JTextField();
        valBox.setText(String.valueOf(guiStore.getBox().getBox().getBoxinitial()));
        valBox.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(initButton, gbc);
        gbc.gridx = 1;
        centerPanel.add(valBox, gbc);

        JButton abonoButoon = new JButton("Abono a CAJA");
        abonoButoon.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField abonoBox = new JTextField();
        abonoBox.setPreferredSize(new Dimension(100, 25));
        JTextField raAbonoBox = new JTextField();
        raAbonoBox.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(abonoButoon, gbc);
        gbc.gridx = 1;
        centerPanel.add(abonoBox, gbc);
        gbc.gridx = 2;
        centerPanel.add(raAbonoBox, gbc);

        JButton payButoon = new JButton("Pago de CAJA");
        payButoon.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField payBox = new JTextField();
        payBox.setPreferredSize(new Dimension(100, 25));
        JTextField raPayBox = new JTextField();
        raPayBox.setPreferredSize(new Dimension(100, 25));

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(payButoon, gbc);
        gbc.gridx = 1;
        centerPanel.add(payBox, gbc);
        gbc.gridx = 2;
        centerPanel.add(raPayBox, gbc);

        JButton totaltxt = new JButton("Calcular total en comandas");
        totaltxt.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(totaltxt, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setOpaque(false);

        ImageIcon backIcon = new ImageIcon(String.valueOf(ImageIO.read(getClass().getResourceAsStream("/Icons\\back.png"))));
        Image backImage = backIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledBackIcon = new ImageIcon(backImage);
        JButton backButoon = new JButton("Atras",scaledBackIcon);
        backButoon.setForeground(Color.WHITE);
        backButoon.setBackground(Color.black);
        backButoon.setFont(new Font("Serif", Font.ITALIC, 14));
        backButoon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(backButoon);

        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        initButton.addActionListener(e -> {
            if (guiStore.getBox().getBox().getBoxinitial() == 0) {
                try {
                    int value = Integer.parseInt(valBox.getText());
                    guiStore.getBox().getBox().setBoxinitial(value);
                    guiStore.getBox().writeToFile();
                    JOptionPane.showMessageDialog(null, "CAJA INICIADA");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                valBox.setText(String.valueOf(guiStore.getBox().getBox().getBoxinitial()));
                JOptionPane.showMessageDialog(null, "No se puede modificar el valor de caja inicial", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        abonoButoon.addActionListener(e -> {
            try {
                int value = Integer.parseInt(abonoBox.getText());
                guiStore.getBox().getBox().setAdicion(value,raAbonoBox.getText());
                guiStore.getBox().writeToFile();
                JOptionPane.showMessageDialog(null, "ABONO ECHO A CAJA");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        payButoon.addActionListener(e -> {
            try {
                int value = Integer.parseInt(payBox.getText());
                guiStore.getBox().getBox().setPago(value,raPayBox.getText());
                guiStore.getBox().writeToFile();
                JOptionPane.showMessageDialog(null, "PAGO DE "+raPayBox.getText()+" REALIZADO");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        totaltxt.addActionListener(e -> {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            try {
                LogicAlcala.addTotalSumToFile("Factura_" + formattedDate + ".txt");
                LogicAlcala.addTotalSumToFile("Factura_" + formattedDate + "_DATAFONO.txt");
                LogicAlcala.addTotalSumToFile("Factura_" + formattedDate + "_TRANSFERENCIA.txt");
                JOptionPane.showMessageDialog(null, "COMANDAS TOTALIZADAS");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        backButoon.addActionListener(e -> {
            try {
                guiStore.showCustomerMenuPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
