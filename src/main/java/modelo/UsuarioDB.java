
package modelo;

import java.sql.*;


public class UsuarioDB {
    public static int insert(Usuario usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO USUARIO "
                + "(NOMBRE, EMAIL, CONTRASEÑA, LOGRO) "
                + "VALUES (?, ?, ?, 0)";
        
        try {
            ps = connection.prepareStatement(query,
            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            int res = 0;
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                res = rs.getInt(1);
            }
            
            ps.close();
            pool.freeConnection(connection);
            return res;
            } catch (SQLException e) {
            e.printStackTrace();
            return 0;
            }
    }   
    
    
    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Email FROM Usuario "
        + "WHERE Email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static Usuario selectUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Usuario WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Usuario usuario = null;


            if (rs.next()) {
                usuario = new Usuario();
                usuario.setUsername(rs.getString("Nombre"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("Contraseña"));
                usuario.setLogros(rs.getInt("Logro"));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
          }       
    } 
        
}


