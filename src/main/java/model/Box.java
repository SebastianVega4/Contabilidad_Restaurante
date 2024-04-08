package model;

import logic.LogicAlcala;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Box {
    private int boxinitial;
    private final ArrayList<Integer> pagos;
    private final ArrayList<String> referPagos;
    private final ArrayList<Integer> adiciones;
    private final ArrayList<String> referadiciones;

    public Box() {
        boxinitial = 0;
        pagos = new ArrayList<>();
        referPagos = new ArrayList<>();
        adiciones = new ArrayList<>();
        referadiciones = new ArrayList<>();

    }

    public int getBoxinitial() {
        return boxinitial;
    }

    public void setBoxinitial(int boxinitial) {
        this.boxinitial = boxinitial;
    }

    public ArrayList<String> getReferPagos() {
        return referPagos;
    }

    public ArrayList<Integer> getPagos() {
        return pagos;
    }

    public void setPago(int pago, String referencia) {
        pagos.add(pago);
        referPagos.add(referencia);
    }

    public ArrayList<Integer> getAdiciones() {
        return adiciones;
    }

    public ArrayList<String> getReferadiciones() {
        return referadiciones;
    }

    public void setAdicion(int adicion, String referadicion) {
        adiciones.add(adicion);
        referadiciones.add(referadicion);
    }

    public int calTotal() throws IOException {
        int totalPagos = 0, totalAdiciones = 0;
        for (int i = 0; i < getAdiciones().size(); i++) {
            totalAdiciones += getAdiciones().get(i);
        }

        for (int i = 0; i < getPagos().size(); i++) {
            totalPagos += getPagos().get(i);
        }
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        return (int) ((boxinitial + totalAdiciones) - totalPagos + LogicAlcala.sumTotalFromFiles("Factura_" + formattedDate + ".txt"));
    }
}
