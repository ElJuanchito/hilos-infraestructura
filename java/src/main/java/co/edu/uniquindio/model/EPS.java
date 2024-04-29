package co.edu.uniquindio.model;

import java.util.ArrayList;
import java.util.List;

public class EPS {

    private List<Cita> citas;
    private int asesores;

    public EPS(int asesores) {
        this.citas = new ArrayList<>();
        this.asesores = asesores;
    }

    public synchronized void agregarCita(Cita cita) {
        int i = 0;
        while (i < citas.size() && citas.get(i).getTiempo() < cita.getTiempo()) {
            i++;
        }
        citas.add(i, cita);
    }

    public synchronized Cita eliminarUltima() {
        if (!citas.isEmpty()) {
            return citas.remove(citas.size() - 1);
        } else {
            return null;
        }
    }

    public synchronized Cita eliminarPrimera() {
        if (!citas.isEmpty()) {
            return citas.remove(0);
        } else {
            return null;
        }
    }

    public void atender(String asesor, boolean cortas) {
        while (!citas.isEmpty()) {
            Cita cita;
            if (cortas) {
                cita = eliminarPrimera();
            } else {
                cita = eliminarUltima();
            }
            System.out.println("[" + asesor + "] => Atendiendo cita: " + cita.getNombre() + ", duracion: " + cita.getTiempo());
            try {
                Thread.sleep(cita.getTiempo() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void atenderCitas() {
        List<Thread> hilosAtencion = new ArrayList<>();
        for (int i = 0; i < asesores; i++) {
            final int index = i; // Variable final para usar en la expresión lambda
            Thread hilo = new Thread(() -> atender("Asesor " + index, index % 2 == 0));
            hilosAtencion.add(hilo);
            hilo.start();
        }

        for (Thread hiloAtencion : hilosAtencion) {
            try {
                hiloAtencion.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
