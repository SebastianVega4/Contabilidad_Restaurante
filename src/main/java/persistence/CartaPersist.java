package persistence;

import java.io.*;

public class CartaPersist {
    public void writeToFile() throws IOException {
        String currentDir = System.getProperty("user.dir");
        File directory = new File(currentDir, "Facturas/Base de Datos");
        if (!directory.exists()) {
            boolean success = directory.mkdirs();
            System.out.println("Directorio 'Facturas' creado");
            if (!success) {
                System.err.println("No se pudo crear el directorio 'Facturas'");
                return;
            }
        }

        File file = new File(directory, "ProductsAlcala.txt");

        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("""
                        1,Ceviche de camarones,Null,18000.0,1000
                        2,Chorizos artesanales,Bañados co...,15000.0,1000
                        3,Papitas chorreadas,Gratinadas...,12000.0,1000
                        4,Patacón con ahogado,Null,12000.0,1000
                        5,Sopa tipica,Null,9000.0,1000
                        6,Oio de bife,400 gr de ...,45000.0,1000
                        7,Baby Alcalá,380 gr de ...,48000.0,1000
                        8,Baby beef,380 gr de ...,45000.0,1000
                        9,Bife chorizo,400 gr de ...,45000.0,1000
                        10,Punta de anca,400 gr Pun...,47000.0,1000
                        11,Churrasco,400 gr de ...,47000.0,1000
                        12,Trifásico Alcalá,180 gr de ...,48000.0,1000
                        13,Picada pequeña (2 personas),Lomo de ce...,68000.0,1000
                        14,Picada mediana (4 personas),Chatas lom...,120000.0,1000
                        15,Picada Alcalá (6 personas),Chatas lom...,170000.0,1000
                        16,Lomo de cerdo a la parrilla,400 gr de ...,39000.0,1000
                        17,Lomo de cerdo agridulce,400 gr de ...,39000.0,1000
                        18,Pechuga de pollo a la parrilla,350 gr de ...,32000.0,1000
                        19,Pechuga en salsa de champiñones,350 gr de ...,35000.0,1000
                        20,Cazuela de mariscos,Mezcla en ...,46000.0,1000
                        21,Salmon Marinero,300 gr de ...,65000.0,1000
                        22,Salmon Citrico,300 gr de ...,54000.0,1000
                        23,Salmon Plancha,300 gr de ...,50000.0,1000
                        24,Trucha Alcala ,Filete de ...,45000.0,1000
                        25,Trucha al ajillo,400 gr de ...,35000.0,1000
                        26,Trucha en salsa de almendras,400 gr de ...,38000.0,1000
                        27,Trucha mandarina,400 gr de ...,35000.0,1000
                        28,Robalo en salsa de camarón,300 gr de ...,62000.0,1000
                        29,Robalo a la marinera,300 gr de ...,64000.0,1000
                        30,Robalo a la plancha,300 gr de ...,49000.0,1000
                        31,Arepa boyacense,Null,4000.0,1000
                        32,Porción de Chorizo (2und),Null,10000.0,1000
                        33,Porción de Rellena (2und),Null,9000.0,1000
                        34,Porción papa a la francesa,Null,6000.0,1000
                        35,Recipiente desechable para llevar,Null,1500.0,1000
                        36,Pechuga Jr,1/2 Pechug...,22000.0,1000
                        37,Limonada natural,Null,5000.0,1000
                        38,Jugos naturales,Null,5000.0,1000
                        39,Sorbete de fruta natural,Null,6000.0,1000
                        40,Gaseosa,Null,4000.0,1000
                        41,Botella de agua,con o sin ...,3000.0,1000
                        42,Cerveza,(águila po...,4000.0,1000
                        43,Club Colombia,Null,5000.0,1000
                        44,Guarapo de sabores,(martacuya...,9000.0,1000
                        45,Cerveza artesanal bruder,Null,10000.0,1000
                        46,Jarra Liminada Pequeña,Null,13000.0,1000
                        47,Jarra Liminada Grande,Null,25000.0,1000
                        """);
            }
            System.out.println("Bases de datos creada");
        }else {
            System.err.println("Bases de datos ya existente");
        }
    }
}