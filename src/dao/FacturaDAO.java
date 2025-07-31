package dao;

import model.Factura;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public void insertar(Factura factura) {
        String sql = "INSERT INTO facturas(fecha, id_cliente, total) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(factura.getFecha().getTime()));
            stmt.setInt(2, factura.getIdCliente());
            stmt.setBigDecimal(3, factura.getTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Factura> listar() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas";
        try (Connection con = ConexionBD.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Factura f = new Factura();
                f.setIdFactura(rs.getInt("id_factura"));
                f.setFecha(rs.getDate("fecha"));
                f.setIdCliente(rs.getInt("id_cliente"));
                f.setTotal(rs.getBigDecimal("total"));
                lista.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Factura factura) {
        String sql = "UPDATE facturas SET fecha=?, id_cliente=?, total=? WHERE id_factura=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(factura.getFecha().getTime()));
            stmt.setInt(2, factura.getIdCliente());
            stmt.setBigDecimal(3, factura.getTotal());
            stmt.setInt(4, factura.getIdFactura());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM facturas WHERE id_factura=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
