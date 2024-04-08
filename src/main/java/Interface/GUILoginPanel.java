package Interface;

import logic.LogicAlcala;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUILoginPanel {
    private final JPanel panel;
    private final LogicAlcala logicAlcala;

    public GUILoginPanel(GUIstore guiStore) throws IOException {
        this.logicAlcala = guiStore.getLogicAlcala();
        //Fondo
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = null;
                try {
                    backgroundImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/login.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        //Panel Arriba
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        //logo empresa arriba a la izquierda
        ImageIcon imageLogo = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Icons/logo.png")));
        Image imageLog = imageLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledImageLogo = new ImageIcon(imageLog);
        JLabel imgLog = new JLabel(scaledImageLogo);
        topPanel.add(imgLog, BorderLayout.WEST);//añadir imagen con ubicaion

        // Panel Center para login
        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false);
        loginPanel.setLayout(new GridLayout(4, 1));

        //panel grid para cada uno de los labels y field en grupos
        JPanel emailPanel = new JPanel(new GridBagLayout());
        emailPanel.setOpaque(false);
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Serif", Font.ITALIC, 18));
        JTextField userField = new JTextField(24);
        userField.setText("admin");
        userField.setFont(new Font("Serif", Font.ITALIC, 16));
        userField.setOpaque(false);
        userField.setForeground(Color.white);
        userField.setBorder(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        emailPanel.add(userLabel, gbc);
        gbc.gridy = 1;
        emailPanel.add(userField, gbc);
        loginPanel.add(emailPanel);

        JPanel passwordPanel = new JPanel(new GridBagLayout());
        passwordPanel.setOpaque(false);
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Serif", Font.ITALIC, 18));
        JPasswordField passwordField = new JPasswordField(24);
        passwordField.setText("1234");
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.white);
        passwordField.setBorder(null);
        gbc.gridx = 0;
        gbc.gridy = 0;
        passwordPanel.add(passwordLabel, gbc);
        gbc.gridy = 1;
        passwordPanel.add(passwordField, gbc);
        loginPanel.add(passwordPanel);

        //panel de Abajo
        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttomPanel.setOpaque(false);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.black);
        loginButton.setFont(new Font("Serif", Font.ITALIC, 11));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttomPanel.add(loginButton);

        //añadir al panel Padre Login con borde y bien ubicado
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(loginPanel, BorderLayout.CENTER);
        panel.add(buttomPanel, BorderLayout.SOUTH);

        userField.addActionListener(e -> loginButton.doClick()); // simula un clic en el botón de inicio de sesión cuando se presiona Enter en el campo de usuario
        passwordField.addActionListener(e -> loginButton.doClick());

        //capturar evento del boton login
        loginButton.addActionListener(e -> {
            if (logicAlcala.login(userField.getText(), new String(passwordField.getPassword()))) {
                try {
                    guiStore.showCustomerMenuPanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(guiStore.getFrame(), "Credenciales inválidas. Inténtalo de nuevo.");
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}