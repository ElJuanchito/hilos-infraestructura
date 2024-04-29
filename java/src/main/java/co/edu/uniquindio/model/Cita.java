package co.edu.uniquindio.model;

import lombok.Getter;

@Getter
public class Cita implements Comparable<Cita> {
    private String nombre;
    private int tiempo;

    public Cita(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    @Override
    public int compareTo(Cita other) {
        return Integer.compare(this.tiempo, other.tiempo);
    }
}
