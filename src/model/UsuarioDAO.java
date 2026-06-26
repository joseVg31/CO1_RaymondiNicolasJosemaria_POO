package model;

import util.Conexion;
import java.sql.*;

public class UsuarioDAO {

    public Usuario autenticar(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("contrasena")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al autenticar: " + e.getMessage());
        }
        return null;
    }
}
