package logic;

import Interface.GUIstore;
import model.Administrator;
import model.Order;
import model.Product;
import model.ShoppingComan;
import persistence.ImageProducts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogicAlcala {
    private final ImageProducts ip;
    private final ShoppingComan shoppingComan = new ShoppingComan();
    private Order order;
    private String facture = "";
    private String tipeTransf;
    private int propina;

    public LogicAlcala() {
        ip = new ImageProducts();
        propina = 0;
    }

    public static double sumTotalFromFiles(String nameArchi) throws IOException {
        final double[] totalSum = {0.0};
        Pattern pattern = Pattern.compile("Total:\\s*\\$(\\d+(\\.\\d+)?)");

        // Obtén el directorio actual
        String currentDir = System.getProperty("user.dir");

        try (Stream<Path> paths = Files.list(Paths.get(currentDir))) {
            paths.filter(p -> p.getFileName().toString().startsWith(nameArchi))
                    .forEach(filePath -> {
                        try (Stream<String> lines = Files.lines(filePath)) {
                            lines.forEach(line -> {
                                Matcher matcher = pattern.matcher(line);
                                if (matcher.find()) {
                                    totalSum[0] += Double.parseDouble(matcher.group(1));
                                }
                            });
                        } catch (IOException e) {
                            System.err.println("Error al leer el archivo: " + filePath);
                        }
                    });
        }

        return totalSum[0];
    }

    public static void addTotalSumToFile(String nameArchivo) throws IOException {

        // Obtén el directorio actual
        String currentDir = System.getProperty("user.dir");

        double totalSum = sumTotalFromFiles(nameArchivo);

        File file = new File(currentDir, nameArchivo);
        // Verifica si el archivo existe
        if (file.exists()) {
            // Escribe en el archivo
            try (FileWriter writerFacture = new FileWriter(file, true)) {
                try {
                    writerFacture.write("\nTotal de todas las facturas: $" + totalSum);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //logica iniciar sesion
    public boolean login(String user, String password) {
        if (Administrator.getName().equals(user)) {
            return Administrator.getPassword().equals(password);

        }
        return false;
    }

    //logica saber cuantos productos compra y añadir al carrito
    public void addNumberPurchesed(Product product, int amount) {
        shoppingComan.setPurchased(product, amount);
    }

    public String addPurchased(Product product) {
        shoppingComan.addProduct(product);
        return "Articulo: ' " + product.getNameProduct() + " ' añadido al carrito.";
    }

    //logica eliminar producto del carrito
    public void eraseProductCart(Product product) {
        shoppingComan.eraseProduct(product);
    }

    //logica vaciar carrito de compra
    public void clearCart() {
        shoppingComan.getProducts().clear();
    }

    //logica para realizar la compra
    public void makePurchase(String tipeFacture) throws IOException {
        order = new Order(shoppingComan);
        setFacture(tipeFacture);
        createFileFacture(tipeFacture);
        lessInventary();
        clearCart();
    }

    public void lessInventary() throws IOException {
        for (Product product : shoppingComan.getProducts()) {
            int numberPurchased = shoppingComan.getPurchased(product);
            product.setStock(product.getStock() - numberPurchased);
        }
        GUIstore.getInventory().updateProductToTxt();
    }

    public String getFacture() {
        return facture;
    }

    public void setFacture(String tipeFacture) {
        if (shoppingComan.getProducts().isEmpty()) {
            facture = """
                               
                               Administrador
                      No tiene productos en el carrito
                      
                      
                    """;
        } else {
            facture = """
                    -----------------------------------------------
                              RESTAURANTE ALCALA
                    Su facura se ha generado con exito
                    """;

            for (Product orderliness : order.getShoppingCart().getProducts()) {
                facture += String.format("""
                        -- _________________________________ --
                           Producto:  %s
                           Cantidad:  %s
                           Precio:    %s
                        """, orderliness.getNameProduct(), order.getShoppingCart().getPurchased(orderliness), orderliness.getPrice());
            }

        }
        facture += String.format("""
                :_______________________________:            Total:  $%s
                """, shoppingComan.calcTotal());
        if (tipeFacture.equals("transferencia")) {
            facture += "                                           " + tipeTransf + "\n \n";
        }
        if (tipeFacture.equals("datafono")) {
            facture += "                                         Propina en ese valor de:" + propina + "\n \n";
        }

    }

    //crear archivo por cada factura diaria
    public void createFileFacture(String tipeFacture) throws IOException {
        // Obtén el directorio actual
        String currentDir = System.getProperty("user.dir");

        // Obtén la fecha actual y formateala en el formato deseado
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        File file = new File(currentDir, "Factura_" + formattedDate + ".txt");

        // Crea un archivo en el directorio actual con el nombre "factura_{fecha}.txt"
        switch (tipeFacture) {
            case "transferencia" -> file = new File(currentDir, "Factura_" + formattedDate + "_TRANSFERENCIA.txt");
            case "datafono" -> file = new File(currentDir, "Factura_" + formattedDate + "_DATAFONO.txt");
            case "noramaly" -> file = new File(currentDir, "Factura_" + formattedDate + ".txt");
        }

        // Verifica si el archivo existe
        if (!file.exists()) {
            // Si no existe, crea el archivo
            file.createNewFile();
        }

        // Escribe en el archivo
        try (FileWriter writerFacture = new FileWriter(file, true)) {
            try {
                writerFacture.write(facture + "\n \n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setTipeTransfe(String tipoDeTransferencia) {
        tipeTransf = tipoDeTransferencia;
    }

    public void setPropina(int pro) {
        propina = pro;
    }

    public ShoppingComan getCart() {
        return shoppingComan;
    }
}