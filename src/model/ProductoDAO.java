package model;

import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // ── Registrar ─────────────────────────────────────────────
    public boolean registrar(Producto p) {
        String sql = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // ── Modificar ─────────────────────────────────────────────
    public boolean modificar(Producto p) {
        String sql = "UPDATE productos SET nombre=?, categoria=?, precio=?, stock=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    // ── Eliminar ──────────────────────────────────────────────
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    // ── Buscar por nombre ─────────────────────────────────────
    public List<Producto> buscar(String nombre) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar: " + e.getMessage());
        }
        return lista;
    }

    // ── Listar todos ──────────────────────────────────────────
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // ── Obtener por ID ────────────────────────────────────────
    public Producto obtenerPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);

        } catch (SQLException e) {
            System.err.println("Error al obtener por ID: " + e.getMessage());
        }
        return null;
    }

    // ── Mapear ResultSet → Producto ───────────────────────────
    private Producto mapear(ResultSet rs) throws SQLException {
        return new Producto(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("categoria"),
            rs.getDouble("precio"),
            rs.getInt("stock")
        );
    }
}
