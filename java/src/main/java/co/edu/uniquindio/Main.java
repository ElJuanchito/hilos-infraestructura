package co.edu.uniquindio;

import co.edu.uniquindio.model.Asesor;
import co.edu.uniquindio.model.Cita;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Cita[][] calendario = new Cita[7][24];
        inicializarCalendario(calendario);

        Asesor asesor1 = new Asesor(calendario, 4, 15);
        Asesor asesor2 = new Asesor(calendario, 4, 15);

        Thread hiloAsesor1 = new Thread(asesor1);
        Thread hiloAsesor2 = new Thread(asesor2);

        hiloAsesor1.start();
        hiloAsesor2.start();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2); // Crear un pool de hilos con 2 hilos
        executor.schedule(() -> asesor1.detenerDespuesDe30Segundos(), 10, TimeUnit.SECONDS);
        executor.schedule(() -> asesor2.detenerDespuesDe30Segundos(), 10, TimeUnit.SECONDS);
    }

    private static void inicializarCalendario(Cita[][] calendario) {
        for (int i = 0; i < calendario.length; i++) {
            for (int j = 0; j < calendario[i].length; j++) {
                calendario[i][j] = new Cita(i, j);
            }
        }
    }
}
