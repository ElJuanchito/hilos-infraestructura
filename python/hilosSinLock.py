import threading
import queue
import time

class EPS:
    def __init__(self, asesores):
        self.citas = []
        self.asesores = asesores

    def agregarCita(self, cita):
        i = 0
        while i < len(self.citas) and self.citas[i].tiempo < cita.tiempo:
            i+=1
        self.citas.insert(i, cita)

    def eliminarUltimo(self):
        if self.citas:
            return self.citas.pop()
        else:
            return None

    def eliminarPrimero(self):
        if self.citas:
            return self.citas.pop(0)
        else:
            return None


    def atender(self, asesor, cortas):
        while self.citas:
            cita = None
            if(cortas):
                cita = self.eliminarPrimero()
            else:
                cita = self.eliminarUltimo()
            print(f"[{asesor}] => Atendiendo cita: {cita.nombre}, duracion: {cita.tiempo}")
            time.sleep(cita.tiempo)

    def atenderCitas(self):
        hilos_atencion = []
        for i in range(self.asesores):
            hilo = threading.Thread(target=self.atender, args=(f"Asesor {i}", i % 2))
            hilos_atencion.append(hilo)
            hilo.start()

        for hilo_atencion in hilos_atencion:
            hilo_atencion.join()

class Cita:
    def __init__(self, nombre, tiempo):
        self.nombre = nombre
        self.tiempo = tiempo

def main():
    eps = EPS(2)

    tiempos = [3, 1, 1, 4, 3, 3]
    print("########################")
    print("#    Adicion Citas     #")
    print("########################")
    for i in range(len(tiempos)):
        cita = Cita(f"Cita { i + 1 }", tiempos[i])
        eps.agregarCita(cita)
        print(f"Nueva cita: Cita { i + 1 }, duracion: {tiempos[i]}")
        time.sleep(0.5)

    print("########################")
    print("#    Atencion Citas    #")
    print("########################")
    eps.atenderCitas()

if __name__ == "__main__":
    main()
