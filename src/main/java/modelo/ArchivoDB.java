
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class ArchivoDB {
    /**
     * Insertar un nuevo archivo en la base de datos
     * @param archivo
     * @return 
     */
    public static int insert(Archivo archivo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO ARCHIVO "
                + "(NOMBRE, DESCRIPCION, UNIVERSIDAD, GRADO, CURSO, CUATRIMESTRE, ASIGNATURA) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query,
            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, archivo.getNombre());
            ps.setString(2, archivo.getDescripcion());
            ps.setString(3, archivo.getUniversidad());
            ps.setString(4, archivo.getGrado());
            ps.setInt(5, archivo.getCurso());
            ps.setInt(6, archivo.getCuatrimestre());
            ps.setString(7, archivo.getAsignatura());
            int res = ps.executeUpdate();
   
            ps.close();
            
            return res;
            } catch (SQLException e) {
            e.printStackTrace();
            return 0;
            }
    }
    
    
}
