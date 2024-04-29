#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <queue>
#include <chrono>

using namespace std;

class Cita {
public:
    Cita(const string& nombre, int tiempo) : nombre(nombre), tiempo(tiempo) {}

    string nombre;
    int tiempo;
};

class EPS {
public:
    EPS(int asesores) : asesores(asesores) {}

    void agregarCita(const Cita& cita) {
        lock_guard<mutex> lock(mutex_);
        citas.push(cita);
    }

    Cita eliminarPrimero() {
        lock_guard<mutex> lock(mutex_);
        if (!citas.empty()) {
            Cita cita = citas.front();
            citas.pop();
            return cita;
        } else {
            return Cita("", 0);
        }
    }

    void atender(const string& asesor, bool cortas) {
        while (true) {
            Cita cita = eliminarPrimero();
            if (cita.tiempo == 0) {
                break;
            }
            cout << "[" << asesor << "] => Atendiendo cita: " << cita.nombre << ", duracion: " << cita.tiempo << endl;
            this_thread::sleep_for(chrono::seconds(cita.tiempo));
        }
    }

    void atenderCitas() {
        vector<thread> hilos_atencion;
        for (int i = 0; i < asesores; ++i) {
            hilos_atencion.emplace_back(&EPS::atender, this, "Asesor " + to_string(i), i % 2);
        }

        for (auto& hilo_atencion : hilos_atencion) {
            hilo_atencion.join();
        }
    }

private:
    queue<Cita> citas;
    int asesores;
    mutex mutex_;
};

int main() {
    EPS eps(2);

    vector<int> tiempos = {3, 1, 1, 4, 3, 3};
    cout << "########################" << endl;
    cout << "#    Adicion Citas     #" << endl;
    cout << "########################" << endl;
    for (size_t i = 0; i < tiempos.size(); ++i) {
        Cita cita("Cita " + to_string(i + 1), tiempos[i]);
        eps.agregarCita(cita);
        cout << "Nueva cita: Cita " << i + 1 << ", duracion: " << tiempos[i] << endl;
        this_thread::sleep_for(chrono::milliseconds(500));
    }

    cout << "########################" << endl;
    cout << "#    Atencion Citas    #" << endl;
    cout << "########################" << endl;
    eps.atenderCitas();

    return 0;
}