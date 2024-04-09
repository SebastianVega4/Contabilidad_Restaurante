package Interface;

import persistence.CartaPersist;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CartaPersist carta = new CartaPersist();
        carta.writeToFile();
        GUIstore gUIstore = new GUIstore();
        gUIstore.getFrame().setVisible(true);
        gUIstore.showLoginPanel();
    }
}