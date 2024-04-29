package co.edu.uniquindio.model;

import lombok.Data;

@Data
public class Cita {
    private int dia;
    private int hora;
    private boolean ocupada;

    public Cita(int dia, int hora) {
        this.dia = dia;
        this.hora = hora;
        this.ocupada = false;
    }
}
