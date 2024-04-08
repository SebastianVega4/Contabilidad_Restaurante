package persistence;

import logic.LogicAlcala;
import model.Box;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CajaPersis {
    private final Box box = new Box();

    public void readFromFile() throws IOException {
        String currentDir = System.getProperty("user.dir");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        File file = new File(currentDir, "Factura_" + formattedDate + "_CIERRE_CAJA.txt");

        if (!file.exists()) {
            file.createNewFile();
            writeToFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length < 2) {
                        continue;
                    }
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    switch (key) {
                        case "Caja Inicial":
                            box.setBoxinitial(Integer.parseInt(value));
                            break;
                        case "Adicion":
                            String[] adicionParts = value.split(",");
                            if (adicionParts.length < 2) {
                                continue;
                            }
                            box.setAdicion(Integer.parseInt(adicionParts[0].trim()), adicionParts[1].trim());
                            break;
                        case "Pago":
                            String[] pagoParts = value.split(",");
                            if (pagoParts.length < 2) {
                                continue;
                            }
                            box.setPago(Integer.parseInt(pagoParts[0].trim()), pagoParts[1].trim());
                            break;
                    }
                }
            }
        }
    }

    public void writeToFile() throws IOException {
        String currentDir = System.getProperty("user.dir");

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        File file = new File(currentDir, "Factura_" + formattedDate + "_CIERRE_CAJA.txt");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("-----------------------------------------\n");
            writer.write("Caja Inicial: " + box.getBoxinitial() + "\n");

            for (int i = 0; i < box.getAdiciones().size(); i++) {
                writer.write("Adicion: " + box.getAdiciones().get(i) + ", " + box.getReferadiciones().get(i) + "\n");
            }

            for (int i = 0; i < box.getPagos().size(); i++) {
                writer.write("Pago: " + box.getPagos().get(i) + ", " + box.getReferPagos().get(i) + "\n");
            }
            writer.write("Ventas: " + LogicAlcala.sumTotalFromFiles("Factura_" + formattedDate + ".txt") + "\n");
            writer.write("-----------------------------------------\n");
            writer.write("TOTAL Caja: " + box.calTotal());
            writer.write("\n\nTOTAL Datafono: " + LogicAlcala.sumTotalFromFiles("Factura_" + formattedDate + "_DATAFONO.txt"));
            writer.write("\nTOTAL Transferencias: " + LogicAlcala.sumTotalFromFiles("Factura_" + formattedDate + "_TRANSFERENCIA.txt"));
            writer.write("\n\nTOTAL ventas : " + (box.calTotal() +
                    LogicAlcala.sumTotalFromFiles("Factura_" + formattedDate + "_DATAFONO.txt") +
                    LogicAlcala.sumTotalFromFiles("Factura_" + formattedDate + "_TRANSFERENCIA.txt")));
        }

    }

    public Box getBox() {
        return box;
    }
}