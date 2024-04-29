package co.edu.uniquindio;

import co.edu.uniquindio.model.Cita;
import co.edu.uniquindio.model.EPS;

public class Main {
    public static void main(String[] args) {
        EPS eps = new EPS(2);

        int[] tiempos = {3, 1, 1, 4, 3, 3};
        System.out.println("########################");
        System.out.println("#    Adicion Citas     #");
        System.out.println("########################");
        for (int i = 0; i < tiempos.length; i++) {
            Cita cita = new Cita("Cita " + (i + 1), tiempos[i]);
            eps.agregarCita(cita);
            System.out.println("Nueva cita: Cita " + (i + 1) + ", duracion: " + tiempos[i]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("########################");
        System.out.println("#    Atencion Citas    #");
        System.out.println("########################");
        eps.atenderCitas();
    }
}
