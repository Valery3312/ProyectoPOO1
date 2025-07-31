import dao.JugadorDAO;
import model.Jugador;

import java.util.List;

public class TestJugadorDAO {
    public static void main(String[] args) {
        JugadorDAO dao = new JugadorDAO();

        // 1. Insertar un jugador
        Jugador nuevo = new Jugador();
        nuevo.setNombre("Luis");
        nuevo.setEdad(20);
        nuevo.setNivel(3);
        nuevo.setPuntaje(1500);
        dao.insertar(nuevo);
        System.out.println("Jugador insertado.");

        // 2. Listar jugadores
        System.out.println("Lista de jugadores:");
        List<Jugador> jugadores = dao.listar();
        for (Jugador j : jugadores) {
            System.out.println(j.getIdJugador() + " - " + j.getNombre() + ", edad: " + j.getEdad() + ", nivel: " + j.getNivel() + ", puntaje: " + j.getPuntaje());
        }

        // 3. Actualizar un jugador (ejemplo: cambiar el nombre del primer jugador)
        if (!jugadores.isEmpty()) {
            Jugador primero = jugadores.get(0);
            primero.setNombre("Luis Actualizado");
            dao.actualizar(primero);
            System.out.println("Jugador actualizado.");
        }

        // 4. Eliminar el último jugador insertado (por id)
        if (!jugadores.isEmpty()) {
            Jugador ultimo = jugadores.get(jugadores.size() - 1);
            dao.eliminar(ultimo.getIdJugador());
            System.out.println("Jugador eliminado con ID: " + ultimo.getIdJugador());
        }
    }
}
