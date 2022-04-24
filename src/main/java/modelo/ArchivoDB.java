
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.Part;


public class ArchivoDB {
    
    private static ArrayList<Archivo> listaArchivos = new ArrayList<>();
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
    
    /**
     * Seleccionar un usuario de la base de datos a partir de su nombre de usuario
     * @return listaArchivos
     */
    public static ArrayList<Archivo> getAllArchivos() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Archivo";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Archivo archivo = null;
            listaArchivos.clear();

            while (rs.next()) {
                archivo = new Archivo(rs.getString("nombre"), rs.getInt("idArchivo"),
                    rs.getInt("propietario"), rs.getString("descripcion"), rs.getString("universidad"),
                    rs.getString("grado"), rs.getInt("curso"), rs.getInt("cuatrimestre"),
                    rs.getString("asignatura"), rs.getInt("numVistas"), rs.getDate("fechaSubida"),
                    rs.getInt("numDescargas"), rs.getDouble("valoracionMedia"), rs.getInt("comentario"),
                    (Part) rs.getBlob("contenido"));
                
                listaArchivos.add(archivo);
                
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaArchivos;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
          }       
    }
    
    
}
