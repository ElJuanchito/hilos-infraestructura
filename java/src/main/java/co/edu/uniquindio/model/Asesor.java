package co.edu.uniquindio.model;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Asesor implements Runnable {
    private final Cita[][] calendario;
    private final int dia, hora;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public Asesor(Cita[][] calendario, int dia, int hora) {
        this.calendario = calendario;
        this.dia = dia;
        this.hora = hora;
    }

    @Override
    public void run() {
        separarCita(dia, hora);
    }

    public void separarCita(int dia, int hora) {
        if (calendario[dia][hora].isOcupada()) {
            System.out.println("La cita se encuentra ocupada");
            return;
        }
        calendario[dia][hora].setOcupada(true);
        String[] datos = llenarDatos();
        String nombre = datos[0];
        int edad = Integer.parseInt(datos[1]);
        if (nombre == null || edad == 0) {
            revertirCita(dia, hora);
            System.out.println("El tiempo ha sido agotado, intente nuevamente");
        } else {
            System.out.printf("Se asignó la cita de las %d el día %d, al paciente %s de edad %d%n", hora, dia, nombre, edad);
        }
    }

    public void detenerDespuesDe30Segundos() {
        running.set(false);
    }

    private String[] llenarDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del paciente");
        String nombre = sc.nextLine();
        System.out.println("Ingrese la edad del paciente");
        int edad = sc.nextInt();
        return new String[]{nombre, String.valueOf(edad)};
    }

    private void revertirCita(int dia, int hora) {
        calendario[dia][hora].setOcupada(false);
    }

}
