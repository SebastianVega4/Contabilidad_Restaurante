package persistence;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageProducts {
    private String rutaYNombreImagen;
    private ImageIcon imagen;
    private final JFileChooser fileChooser;

    public ImageProducts() {
        fileChooser = new JFileChooser();
    }

    public boolean obtainImage(int index) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Copia el archivo seleccionado a la ubicaci�n deseada
                FileInputStream fis = new FileInputStream(selectedFile);
                FileOutputStream fos = new FileOutputStream(String.valueOf(getClass().getResource("/Icons/" + index + ".png")));
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, bytesRead);
                }
                fis.close();
                fos.close();

                // Puedes guardar la ruta del archivo original si lo necesitas
                rutaYNombreImagen = selectedFile.getAbsolutePath();

                // Crea un ImageIcon usando la ruta de la copia
                imagen = new ImageIcon(String.valueOf(getClass().getResource("/Icons/" + index + ".png")));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean renameImage(String newName) {
        if (rutaYNombreImagen != null) {
            File imagenAnterior = new File(rutaYNombreImagen);
            File nuevaImagen = new File(String.valueOf(getClass().getResource("/Icons/" + newName + ".png")));

            if (imagenAnterior.renameTo(nuevaImagen)) {
                rutaYNombreImagen = nuevaImagen.getAbsolutePath();
                return true;
            }
        }
        return false;
    }

    public void DeleteImage(int index) {
        Path target = Paths.get(String.valueOf(getClass().getResource("/Icons/" + index + ".png")));
        try {
            Files.delete(target);
        } catch (IOException e) {
        }
    }

    public ImageIcon getImagen() {
        return imagen;
    }
}
