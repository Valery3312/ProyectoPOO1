package dao;

import model.Jugador;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {
    public void insertar(Jugador jugador) {
        String sql = "INSERT INTO jugadores(nombre, edad, nivel, puntaje) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getEdad());
            stmt.setInt(3, jugador.getNivel());
            stmt.setInt(4, jugador.getPuntaje());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Jugador> listar() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";
        try (Connection con = ConexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt("id_jugador"));

                j.setNombre(rs.getString("nombre"));
                j.setEdad(rs.getInt("edad"));
                j.setNivel(rs.getInt("nivel"));
                j.setPuntaje(rs.getInt("puntaje"));
                lista.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Jugador jugador) {
        String sql = "UPDATE jugadores SET nombre=?, edad=?, nivel=?, puntaje=? WHERE id_jugador=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getEdad());
            stmt.setInt(3, jugador.getNivel());
            stmt.setInt(4, jugador.getPuntaje());
            stmt.setInt(5, jugador.getIdJugador());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM jugadores WHERE id_jugador=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
