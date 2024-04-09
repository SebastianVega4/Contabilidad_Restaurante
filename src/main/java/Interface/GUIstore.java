package Interface;

import logic.LogicAlcala;
import model.Product;
import persistence.CajaPersis;
import persistence.Inventory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIstore {
    private static Inventory inventory = null;
    private final JFrame frame;
    private final LogicAlcala logicAlcala = new LogicAlcala();
    private final CajaPersis box;

    public GUIstore() throws IOException {
        frame = new JFrame("Restaurante Alcala");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        inventory = new Inventory();
        box = new CajaPersis();
        box.readFromFile();
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public CajaPersis getBox() {
        return box;
    }

    public LogicAlcala getLogicAlcala() {
        return logicAlcala;
    }

    public void showLoginPanel() throws IOException {
        GUILoginPanel gUILoginPanel = new GUILoginPanel(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gUILoginPanel.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showMenuPanel()throws IOException {
        GUIMenuPanel gUIMenuPanel = new GUIMenuPanel(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gUIMenuPanel.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showCatalogPanel()throws IOException {
        GUICartaPanel gUICartaPanel = new GUICartaPanel (this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gUICartaPanel.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showCartPanel() throws IOException{
        GUIComanPanel gUIComanPanel = new GUIComanPanel(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gUIComanPanel.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showEditProdut(Product product, int index) throws IOException{
        GUIEditProduct gUIEditPanel = new GUIEditProduct(this, product, index);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gUIEditPanel.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showBoxPanel() throws IOException {
        GUIboxPanel guIboxPanel = new GUIboxPanel(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(guIboxPanel.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void showCalculatorPanel() throws IOException{
        GUIcalculatorPanel guIcalculatorPanel = new GUIcalculatorPanel(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(guIcalculatorPanel.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}