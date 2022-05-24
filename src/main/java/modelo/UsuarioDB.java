
package modelo;

import java.sql.*;


public class UsuarioDB {
    
    /**
     * Insertar un nuevo usuario en la base de datos
     * @param usuario
     * @return 
     */
    public static int insert(Usuario usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO USUARIO "
                + "(NOMBRE, EMAIL, CONTRASEÑA) "
                + "VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query,
            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            int res = ps.executeUpdate();
   
            ps.close();
            
            return res;
            } catch (SQLException e) {
            e.printStackTrace();
            return 0;
            }
    }
    
    /**
     * Valida si el username y la contraseña coinciden con algun usuario del sistema
     * @param user
     * @param password
     * @return 
     */
    public static boolean validate(String user, String password){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Usuario "
        + "WHERE nombre = ? and contraseña = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, password);
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
    
    /**
     * Comprobar si existe un usuario en la base de datos a partir de su email
     * @param email
     * @return 
     */
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
    
    
    /**
     * Comprobar si existe un usuario en la base de datos a partir de su nombre de usuario
     * @param user
     * @return 
     */
    public static boolean userExists(String user){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT nombre FROM Usuario "
        + "WHERE nombre = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
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
    
    /**
     * Seleccionar un usuario en la base de datos a partir de su correo electronico
     * @param email
     * @return 
     */
    public static Usuario selectUserByMail(String email) {
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
    /**
     * Seleccionar un usuario de la base de datos a partir de su nombre de usuario
     * @param username
     * @return 
     */
    public static Usuario selectUserByName(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Usuario WHERE nombre = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            Usuario usuario = null;

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setUsername(rs.getString("Nombre"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("Contraseña"));
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
    
    /**
     * Seleccionar un usuario de la base de datos a partir de su id de usuario
     * @param id
     * @return 
     */
    public static Usuario selectUserById(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Usuario usuario = null;

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setUsername(rs.getString("Nombre"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("Contraseña"));
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


