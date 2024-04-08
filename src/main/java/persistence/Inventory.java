package persistence;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Product> products;

    public Inventory() throws IOException {
        this.products = new ArrayList<>();
        obtainProductToTxt();
    }

    public void obtainProductToTxt() throws IOException {
        // Obtén el directorio actual
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        // Crea un archivo en el directorio actual
        File file = new File(currentDir, "ProductsAlcala.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idProduct = Integer.parseInt(parts[0]);
                String nameProduct = parts[1];
                String description = parts[2];
                if (description.length() > 10) {
                    description = description.substring(0, 10) + "...";
                }
                double price = Double.parseDouble(parts[3]);
                int stock = Integer.parseInt(parts[4]);
                Product product = new Product(idProduct, nameProduct, description, price, stock);
                products.add(product);
            }
        }
    }

    public void updateProductToTxt() throws IOException {
        // Obtén el directorio actual
        String currentDir = System.getProperty("user.dir");

        // Crea un archivo en el directorio actual
        File file = new File(currentDir, "ProductsAlcala.txt");

        try (FileWriter writer = new FileWriter(file)) {
            for (Product product : products) {
                writer.write(product.toString() + "\n");
            }
        }

        // Limpia la lista de productos
        products.clear();

        // Vuelve a leer los productos del archivo de texto
        obtainProductToTxt();
    }

    public void editProduct(String name, String description, String price, String stock, int index) throws IOException {
        Product product = products.get(index - 1);

        // Actualiza los atributos del producto
        product.setNameProduct(name);
        product.setDescription(description);
        product.setPrice(Double.parseDouble(price));
        product.setStock(Integer.parseInt(stock));

        // Guarda los cambios en el archivo
        updateProductToTxt();
    }

    public List<Product> getProducts() {
        return products;
    }
}